package info.fekri.boom.extra

import info.fekri.boom.model.data.MoreUiData
import info.fekri.boom.model.data.PoemsUiData
import info.fekri.boom.model.data.ScienceData
import info.fekri.boom.model.data.HeroesData

interface ScienceEvents {

    fun onScienceItemClicked(book: ScienceData)
    fun onScienceItemLongClicked(book: ScienceData)

}

interface MoreUiEvents {
    fun onMoreUiItemClicked(book: MoreUiData)
    fun onMoreUiItemLongClicked(book: MoreUiData)
}

interface PoemsUiEvents {
    fun onPoemsUiItemClicked(book: PoemsUiData)
    fun onPoemsUiItemLongClicked(book: PoemsUiData)
}

interface HeroesEvent {
    fun onHeroesItemClicked(data: HeroesData.HeroesDataItem)

    fun onHeroesItemLongClicked(data: HeroesData.HeroesDataItem)
}
