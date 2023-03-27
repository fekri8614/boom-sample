package info.fekri.boom.contracts

import info.fekri.boom.model.data.BookRvModel

interface HomeFragmentContract {

    interface Presenter {

        fun onAttach(view: HomeFragmentContract.View)

        fun onDetach()

        fun onImgBooksLongClicked(book: BookRvModel)

        fun onSciBooksLongClicked(book: BookRvModel)

        fun onTopIconsClicked()

    }

    interface View {

        fun showImaginalBooks(data: ArrayList<BookRvModel>)

        fun showScientificBooks(data: ArrayList<BookRvModel>)

        fun showAbilityDialog()

        fun showBooks()

        fun showWelcomeInfoDialog()

    }

}