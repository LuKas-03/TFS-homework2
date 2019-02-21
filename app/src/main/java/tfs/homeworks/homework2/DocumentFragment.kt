package tfs.homeworks.homework2


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DocumentFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(documentNumber: Int): DocumentFragment {
            val args = Bundle()
            args.putInt(ARG_DOCUMENT_NUMBER, documentNumber)
            val fragment = DocumentFragment()
            fragment.arguments = args
            return fragment
        }

        /**
         * ключ, по которому будет храниться номер документа
         */
        private const val ARG_DOCUMENT_NUMBER = "document_number"
    }

    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        number = arguments?.getInt(ARG_DOCUMENT_NUMBER) as Int
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_document, container, false)
        val name = view.findViewById<TextView>(R.id.documentName)
        name.text = "Документ №$number"
        return view
    }

}
