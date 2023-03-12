package info.fekri.boom.extra

import info.fekri.boom.ux.data.MoreUiData
import info.fekri.boom.ux.data.PoemsUiData
import info.fekri.boom.ux.data.ScienceData
import info.fekri.boom.ux.retrofit.models.HeroesData

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
interface HeroesEvent {
    fun onHeroesItemClicked(data: HeroesData.HeroesDataItem)

    fun onHeroesItemLongClicked(data: HeroesData.HeroesDataItem)
}