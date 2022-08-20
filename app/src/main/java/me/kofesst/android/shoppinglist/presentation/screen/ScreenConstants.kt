package me.kofesst.android.shoppinglist.presentation.screen

sealed class ScreenConstants {
    class Auth private constructor() {
        companion object {
            const val ROUTE_NAME = "auth"
        }
    }

    class Home private constructor() {
        companion object {
            const val ROUTE_NAME = "home"
        }
    }

    class ListDetails private constructor() {
        companion object {
            const val ROUTE_NAME = "list"
            const val LIST_ID_ARG_NAME = "id"
        }
    }

    class NewList private constructor() {
        companion object {
            const val ROUTE_NAME = "createList"
        }
    }

    class NewListItem private constructor() {
        companion object {
            const val ROUTE_NAME = "addListItem"
            const val ITEM_INDEX_ARG_NAME = "itemIndex"
        }
    }

    class NewListResult private constructor() {
        companion object {
            const val ROUTE_NAME = "listResult"
            const val LIST_ID_ARG_NAME = "id"
        }
    }
}