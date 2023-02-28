package info.fekri.boom.ux.adapter

import info.fekri.boom.ux.data.Book
import info.fekri.boom.ux.data.BuyBookData

interface ItemEvents {

    fun onItemClicked(book: Book)

}

interface BuyItemEvents {

    fun onBuyItemClicked(buyBookData: BuyBookData)
    fun onBuyItemLongClicked(buyBookData: BuyBookData)

}