package me.kofesst.android.shoppinglist.domain.usecases

import me.kofesst.android.shoppinglist.domain.repository.ShoppingListRepository
import me.kofesst.android.shoppinglist.domain.usecases.validation.*

class UseCases(
    repository: ShoppingListRepository,

    val registerUser: RegisterUser = RegisterUser(repository),
    val logInUser: LogInUser = LogInUser(repository),

    val getLoggedUserUid: GetLoggedUserUid = GetLoggedUserUid(repository),
    val getLoggedUserProfile: GetLoggedUserProfile = GetLoggedUserProfile(repository),

    val saveSession: SaveSession = SaveSession(repository),
    val restoreSession: RestoreSession = RestoreSession(repository),
    val clearSession: ClearSession = ClearSession(repository),

    val saveList: SaveList = SaveList(repository),
    val getList: GetList = GetList(repository),
    val completeList: CompleteList = CompleteList(repository),
    val getOwnedLists: GetOwnedLists = GetOwnedLists(repository),

    val validateForNotNull: ValidateForNotNull = ValidateForNotNull(),
    val validateForEmptyField: ValidateForEmptyField = ValidateForEmptyField(),
    val validateForLength: ValidateForLength = ValidateForLength(),
    val validateForIntRange: ValidateForIntRange = ValidateForIntRange(),
    val validateForEmail: ValidateForEmail = ValidateForEmail(),
    val validateForPassword: ValidateForPassword = ValidateForPassword()
)
