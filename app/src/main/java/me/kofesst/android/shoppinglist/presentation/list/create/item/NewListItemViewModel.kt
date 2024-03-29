package me.kofesst.android.shoppinglist.presentation.list.create.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.kofesst.android.shoppinglist.domain.models.ShoppingItem
import me.kofesst.android.shoppinglist.domain.usecases.UseCases
import me.kofesst.android.shoppinglist.domain.usecases.validation.ValidationResult
import me.kofesst.android.shoppinglist.presentation.utils.Constraints
import me.kofesst.android.shoppinglist.presentation.utils.errorMessage
import javax.inject.Inject

@HiltViewModel
class NewListItemViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    var formState by mutableStateOf(NewItemFormState())

    private val validationChannel = Channel<NewItemFormResult>()
    val formResult = validationChannel.receiveAsFlow()

    fun setEditing(item: ShoppingItem) {
        formState = NewItemFormState(
            name = item.name,
            amount = item.amount
        )
    }

    fun onFormAction(action: NewItemFormAction) {
        when (action) {
            is NewItemFormAction.NameChanged -> {
                formState = formState.copy(name = action.name)
            }
            is NewItemFormAction.AmountChanged -> {
                formState = formState.copy(amount = action.amount)
            }
            NewItemFormAction.Submit -> {
                onSubmit()
            }
        }
    }

    private fun onSubmit() {
        val nameResult = useCases.validateForEmptyField(
            value = formState.name
        ) + useCases.validateForLength(
            value = formState.name,
            lengthRange = Constraints.ShoppingItem.NAME_LENGTH_RANGE
        )

        val amountResult = useCases.validateForNotNull(
            value = formState.amount
        ).run {
            if (this is ValidationResult.Success) {
                useCases.validateForIntRange(
                    value = formState.amount!!,
                    range = Constraints.ShoppingItem.AMOUNT_RANGE
                )
            } else {
                this
            }
        }

        formState = formState.copy(
            nameError = nameResult.errorMessage,
            amountError = amountResult.errorMessage
        )

        val hasError = listOf(
            nameResult,
            amountResult
        ).any { it !is ValidationResult.Success }

        if (!hasError) {
            viewModelScope.launch {
                val item = ShoppingItem(
                    name = formState.name,
                    amount = formState.amount!!
                )
                validationChannel.send(
                    NewItemFormResult.Success(item)
                )
            }
        }
    }
}