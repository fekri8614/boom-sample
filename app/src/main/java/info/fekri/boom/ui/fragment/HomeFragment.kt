package info.fekri.boom.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajat.pdfviewer.PdfViewerActivity
import info.fekri.boom.databinding.FragmentHomeBinding
import info.fekri.boom.ux.adapter.HistoryAdapter
import info.fekri.boom.ux.adapter.ItemEvents
import info.fekri.boom.ux.adapter.PomesAdapter
import info.fekri.boom.ux.adapter.ScienceAdapter
import info.fekri.boom.ux.data.Book

class HomeFragment : Fragment(), ItemEvents {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        scienceUi()
        moreUi()
        poemsUi()
    }

    private fun poemsUi() {
        val data = arrayListOf<Book>(
            Book(
                "One Wod for Kids",
                "It was the first day of school and Stevie was falling asleep",
                "https://m.media-amazon.com/images/I/5102zR0lK+L._AC_UF1000,1000_QL80_.jpg",
                "https://dl3.takbook.com/pdf3/ebook10474[www.takbook.com].pdf"
            ),
            Book(
                "لاک پشت های دریایی",
                "کاوش در دریا ...",
                "https://www.takbook.com/wp-content/uploads/2022/11/lakposht.jpg",
                "https://dl3.takbook.com/pdf3/ebook10413[www.takbook.com].pdf"
            ),
            Book(
                "قلب یک درخت",
                "برای کسانی که می ایستند تا نگاه کنند، درختان داستان می گویند.",
                "http://ketabesabz.com/img/l/5491945609063666.jpg",
                "https://dl3.takbook.com/pdf3/ebook10376[www.takbook.com].pdf"
            ),
            Book(
                "روباه و آقا موشه",
                "آقا موشه، آقا موشه، چرا دماغت کثیفه؟",
                "http://www.istgahekoodak.ir/wp-content/uploads/2017/12/robah-khargoosh02.jpg",
                "https://istgahekoodak.ir/download2/ebook/Roobah%20Va%20Agha%20Mooshe_istgahekoodak.ir.pdf"
            )
        )
        val poemsAdapter = PomesAdapter(data, this)
        binding.recyclerPhilosophy.adapter = poemsAdapter
        binding.recyclerPhilosophy.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun moreUi() {
        val data = arrayListOf<Book>(
            Book(
                "اطلاعات طوریستی کشور های جهان",
                "دور دنیا در 10 دقیقه",
                "http://ketabesabz.com/img/l/9123197403870715.jpg",
                "https://www.ketabha.org/wp-content/uploads/2017/10/around-the-world-in-10-minuteswww.ketabha.org_.pdf"
            ),
            Book(
                "استرالیا را خوب بشناسیم",
                "قلمرو همسود\n" +
                        "(مشترك المنافع) استرالیا (Australia of Commonwealth (کشوري اسـت در ن یمکـره",
                "https://ketabha.org/wp-content/uploads/2017/10/australia.jpg",
                "https://www.ketabha.org/wp-content/uploads/2017/10/Austorolliaketabha.org_.pdf"
            )
        )
        val historyAdapter = HistoryAdapter(data, this)
        binding.recyclerHistory.adapter = historyAdapter
        binding.recyclerHistory.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun scienceUi() {
        val data = arrayListOf<Book>(
            Book(
                "فیزیک جهان ما",
                "ر گذشته انسانها چرایی و ماهیت پدیدههای طبیعی را فراتر از حد ...",
                "https://img.ketabrah.ir/img/l/5311655295587852.jpg",
                "https://dl3.takbook.com/pdf3/ebook10170[www.takbook.com].pdf"
            ),
            Book(
                "DINOSAUR",
                "Eyewitness DINOSAUR",
                "https://www.softgozar.com/Image/Softwares/Screenshot/faneshname-dinasorha_7947_1_SoftGozar.com.JPG",
                "https://www.ketabha.org/wp-content/uploads/2017/08/dainasorswww.ketabha.org_.pdf"
            ),
            Book(
                "آشنایی با کشور ها",
                "افغانستان...",
                "https://ketabha.org/wp-content/uploads/2017/01/70.jpg",
                "https://www.ketabha.org/wp-content/uploads/2017/01/ashnayy_ba_kshvrhawww.ketabha.org_.pdf"
            )
        )
        val scienceAdapter = ScienceAdapter(data, this)
        binding.recycerScience.adapter = scienceAdapter
        binding.recycerScience.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    /*
    when clicked on items, open them.
    */
    override fun onItemClicked(book: Book) {
        startActivity(
            PdfViewerActivity
                .launchPdfFromUrl(
                context,
                book.pdfUrl,
                book.title,
                "",
                false,
            )
        )
    }

}