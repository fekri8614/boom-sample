package info.fekri.boom.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajat.pdfviewer.PdfViewerActivity
import info.fekri.boom.databinding.DialogProfileIconInfoBinding
import info.fekri.boom.databinding.FragmentProfileBinding
import info.fekri.boom.extra.MoreUiEvents
import info.fekri.boom.extra.PoemsUiEvents
import info.fekri.boom.extra.ScienceEvents
import info.fekri.boom.ux.adapter.MoreAdapter
import info.fekri.boom.ux.adapter.PoemsAdapter
import info.fekri.boom.ux.adapter.ScienceAdapter
import info.fekri.boom.ux.data.MoreUiData
import info.fekri.boom.ux.data.PoemsUiData
import info.fekri.boom.ux.data.ScienceData

class ProfileFragment(mContext: Context) : Fragment(), ScienceEvents, MoreUiEvents,
    PoemsUiEvents {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
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
        topProfileUi()
    }

    private fun topProfileUi() {
        readBookIcon()
        setProfileIcon()
    }

    private fun setProfileIcon() {
        binding.icEditImgUser.setOnClickListener { setImageFromGallery() }
    }

    private fun setImageFromGallery() {
        Toast.makeText(
            requireContext().applicationContext,
            "This access will be allowed",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun readBookIcon() {
        /* Show a dialog and close it after 3 seconds */
        binding.itemIconTopReader.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val dialogBinding = DialogProfileIconInfoBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.show()

            Handler().postDelayed({

                dialog.dismiss()

            }, 3000)
        }
    }

    private fun poemsUi() {
        val data = listOf<PoemsUiData>(
            PoemsUiData(
                title = "One Word for Kids",
                desc = "It was the first day of school and Stevie was falling asleep",
                coverImageUrl = "https://m.media-amazon.com/images/I/5102zR0lK+L._AC_UF1000,1000_QL80_.jpg",
                pdfUrl = "https://dl3.takbook.com/pdf3/ebook10474[www.takbook.com].pdf"
            ),
            PoemsUiData(
                title = "لاک پشت های دریایی",
                desc = "کاوش در دریا ...",
                coverImageUrl = "https://www.takbook.com/wp-content/uploads/2022/11/lakposht.jpg",
                pdfUrl = "https://dl3.takbook.com/pdf3/ebook10413[www.takbook.com].pdf"
            ),
            PoemsUiData(
                title = "قلب یک درخت",
                desc = "برای کسانی که می ایستند تا نگاه کنند، درختان داستان می گویند.",
                coverImageUrl = "http://ketabesabz.com/img/l/5491945609063666.jpg",
                pdfUrl = "https://dl3.takbook.com/pdf3/ebook10376[www.takbook.com].pdf"
            ),
            PoemsUiData(
                title = "روباه و آقا موشه",
                desc = "آقا موشه، آقا موشه، چرا دماغت کثیفه؟",
                coverImageUrl = "http://www.istgahekoodak.ir/wp-content/uploads/2017/12/robah-khargoosh02.jpg",
                pdfUrl = "https://istgahekoodak.ir/download2/ebook/Roobah%20Va%20Agha%20Mooshe_istgahekoodak.ir.pdf"
            )
        )

        val poemsAdapter = PoemsAdapter(ArrayList(data), this)
        binding.recyclerPhilosophy.adapter = poemsAdapter
        binding.recyclerPhilosophy.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun moreUi() {
        val data = listOf(
            MoreUiData(
                title = "اطلاعات طوریستی کشور های جهان",
                desc = "دور دنیا در 10 دقیقه",
                coverImageUrl = "http://ketabesabz.com/img/l/9123197403870715.jpg",
                pdfUrl = "https://www.ketabha.org/wp-content/uploads/2017/10/around-the-world-in-10-minuteswww.ketabha.org_.pdf"
            ),
            MoreUiData(
                title = "استرالیا را خوب بشناسیم",
                desc = "قلمرو همسود\n" +
                        "(مشترك المنافع) استرالیا (Australia of Commonwealth (کشوري اسـت در ن یمکـره",
                coverImageUrl = "https://ketabha.org/wp-content/uploads/2017/10/australia.jpg",
                pdfUrl = "https://www.ketabha.org/wp-content/uploads/2017/10/Austorolliaketabha.org_.pdf"
            )
        )

        val moreAdapter = MoreAdapter(ArrayList(data), this)
        binding.recyclerHistory.adapter = moreAdapter
        binding.recyclerHistory.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun scienceUi() {
        val data = listOf(
            ScienceData(
                title = "فیزیک جهان ما",
                desc = "ر گذشته انسانها چرایی و ماهیت پدیدههای طبیعی را فراتر از حد ...",
                coverImageUrl = "https://img.ketabrah.ir/img/l/5311655295587852.jpg",
                pdfUrl = "https://dl3.takbook.com/pdf3/ebook10170[www.takbook.com].pdf"
            ),
            ScienceData(
                title = "DINOSAUR",
                desc = "Eyewitness DINOSAUR",
                coverImageUrl = "https://www.softgozar.com/Image/Softwares/Screenshot/faneshname-dinasorha_7947_1_SoftGozar.com.JPG",
                pdfUrl = "https://www.ketabha.org/wp-content/uploads/2017/08/dainasorswww.ketabha.org_.pdf"
            ),
            ScienceData(
                title = "آشنایی با کشور ها",
                desc = "افغانستان...",
                coverImageUrl = "https://ketabha.org/wp-content/uploads/2017/01/70.jpg",
                pdfUrl = "https://www.ketabha.org/wp-content/uploads/2017/01/ashnayy_ba_kshvrhawww.ketabha.org_.pdf"
            )
        )

        val scienceAdapter = ScienceAdapter(ArrayList(data), this)
        binding.recycerScience.adapter = scienceAdapter
        binding.recycerScience.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    // Events -->
    // Science
    override fun onScienceItemClicked(book: ScienceData) {
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

    override fun onScienceItemLongClicked(book: ScienceData) {
        // delete
    }

    // MoreUi
    override fun onMoreUiItemClicked(book: MoreUiData) {
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

    override fun onMoreUiItemLongClicked(book: MoreUiData) {
        // delete
    }

    // PoemsUi
    override fun onPoemsUiItemClicked(book: PoemsUiData) {
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

    override fun onPoemsUiItemLongClicked(book: PoemsUiData) {
        // delete
    }
}