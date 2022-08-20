package me.kofesst.android.shoppinglist.presentation.create_list.create_item

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.Flow
import me.kofesst.android.shoppinglist.presentation.LocalAppState
import me.kofesst.android.shoppinglist.presentation.create_list.NewListViewModel
import me.kofesst.android.shoppinglist.presentation.screen.Screen
import me.kofesst.android.shoppinglist.presentation.screen.ScreenConstants
import me.kofesst.android.shoppinglist.presentation.screen.TopBarSettings
import me.kofesst.android.shoppinglist.presentation.utils.*
import me.kofesst.android.shoppinglist.ui.components.Buttons
import me.kofesst.android.shoppinglist.ui.components.TextFields

class NewListItemScreen(
    routeName: String
) : Screen<NewListViewModel>(
    routeName = routeName,
    topBarSettings = TopBarSettings(
        visible = true,
        hasBackButton = true,
        title = createEditItemScreenTitle
    ),
    args = listOf(
        navArgument(
            name = ScreenConstants.NewListItem.ITEM_INDEX_ARG_NAME
        ) {
            type = NavType.IntType
            defaultValue = -1
        }
    )
) {
    override val viewModelProducer:
            @Composable (NavHostController, NavBackStackEntry) -> NewListViewModel
        get() = { navController, backStack ->
            val newListScreenEntry = remember(backStack) {
                navController.getBackStackEntry(
                    route = ScreenConstants.NewList.ROUTE_NAME
                )
            }
            hiltViewModel(newListScreenEntry)
        }

    override val content:
            @Composable BoxScope.(NavBackStackEntry, NewListViewModel, Modifier) -> Unit
        get() = { backStack, viewModel, modifier ->
            val formViewModel = hiltViewModel<NewListItemViewModel>()
            val appState = LocalAppState.current
            val itemIndex = this@NewListItemScreen.getIntArg(
                name = ScreenConstants.NewListItem.ITEM_INDEX_ARG_NAME,
                backStackEntry = backStack
            )
            val isEditing = itemIndex >= 0
            LaunchedEffect(Unit) {
                if (isEditing) {
                    val items = viewModel.items
                    formViewModel.setEditing(items[itemIndex])
                }
            }
            val formState = formViewModel.formState
            CreateEditItemForm(
                formState = formState,
                onFormAction = { action ->
                    formViewModel.onFormAction(action)
                },
                modifier = modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
            FormResultListener(
                result = formViewModel.formResult,
                onSuccess = { result ->
                    if (isEditing) {
                        viewModel.updateItem(itemIndex, result.item)
                    } else {
                        viewModel.addItem(result.item)
                    }

                    appState.navController.navigateUp()
                }
            )
        }

    @Composable
    private fun CreateEditItemForm(
        formState: CreateEditItemState,
        onFormAction: (CreateEditItemAction) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 7.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = modifier
        ) {
            ItemNameField(
                name = formState.name,
                errorMessage = formState.nameError,
                onNameChange = {
                    onFormAction(
                        CreateEditItemAction.NameChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            ItemAmountField(
                amount = formState.amount,
                errorMessage = formState.amountError,
                onAmountChange = {
                    onFormAction(
                        CreateEditItemAction.AmountChanged(it)
                    )
                }
            )
            SubmitItemButton(
                onClick = {
                    onFormAction(
                        CreateEditItemAction.Submit
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    private fun ItemNameField(
        name: String,
        errorMessage: UiText?,
        onNameChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        TextFields.OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            errorMessage = errorMessage?.asString(),
            textStyle = MaterialTheme.typography.body1,
            label = itemNameLabel.asString(),
            modifier = modifier
        )
    }

    @Composable
    fun ItemAmountField(
        amount: Int?,
        errorMessage: UiText?,
        onAmountChange: (Int?) -> Unit,
        modifier: Modifier = Modifier
    ) {
        TextFields.OutlinedNumericTextField(
            value = amount,
            onValueChange = onAmountChange,
            errorMessage = errorMessage?.asString(),
            textStyle = MaterialTheme.typography.body1,
            label = itemAmountLabel.asString(),
            modifier = modifier
        )
    }

    @Composable
    private fun SubmitItemButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Buttons.Button(
            text = submitItemText.asString(),
            onClick = onClick,
            modifier = modifier
        )
    }

    @Composable
    private fun FormResultListener(
        result: Flow<CreateEditItemResult>,
        onSuccess: (CreateEditItemResult.Success) -> Unit = {}
    ) {
        LaunchedEffect(Unit) {
            result.collect {
                when (it) {
                    is CreateEditItemResult.Success -> {
                        onSuccess(it)
                    }
                }
            }
        }
    }
}