package info.fekri.boom.extra

import info.fekri.boom.ux.data.*
import info.fekri.boom.ux.retrofit.models.BestBookKTData

interface ScienceEvents {

    fun onScienceItemClicked(book: ScienceData)
    fun onScienceItemLongClicked(book: ScienceData)

}

interface MoreUiEvents {
    fun onMoreUiItemClicked(book:MoreUiData)
    fun onMoreUiItemLongClicked(book: MoreUiData)
}

interface PoemsUiEvents {
    fun onPoemsUiItemClicked(book: PoemsUiData)
    fun onPoemsUiItemLongClicked(book: PoemsUiData)
}

// --------------- BUY ------------------------
interface BuyItemEvents {
    fun onBuyItemClicked(data: BestBookKTData.Item.VolumeInfo)

    fun onBuyItemLongClicked(data: BestBookKTData.Item.VolumeInfo)
}