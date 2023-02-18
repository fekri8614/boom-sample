package info.fekri.boom.ux.adapter

import info.fekri.boom.ux.data.Book

interface ItemEvents {
    fun onItemClicked(book: Book)
}