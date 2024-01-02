package com.senai.vsconnect.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.senai.vsconnect.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditarImagemPerfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Adicionar o ícone do hamburguer apenas nas telas com menu lateral
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_imagem_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val icone_lapis = view.findViewById<ImageView>(R.id.icone_lapis)
        // Adicione um clique ao ícone de lápis
        icone_lapis.setOnClickListener {
            mostrarOpcoesEscolhaImagem()
        }
    }

    private fun mostrarOpcoesEscolhaImagem() {
        val escolherImagemIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val capturarImagemIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val escolherImagemTitle = resources.getString(R.string.escolher_imagem)
        val capturarImagemTitle = resources.getString(R.string.capturar_imagem)

        // Crie uma Intent Chooser para escolher entre a galeria e a câmera
        val chooserIntent = Intent.createChooser(escolherImagemIntent, escolherImagemTitle)
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(capturarImagemIntent))

        startActivityForResult(chooserIntent, IMAGEM_PERFIL_REQUEST_CODE)
    }


    // Constante para o código de solicitação da imagem do perfil
    private val IMAGEM_PERFIL_REQUEST_CODE = 123

    // Manipule o resultado da escolha da imagem
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val profile_image = view?.findViewById<ImageView>(R.id.profile_image)

        if (requestCode == IMAGEM_PERFIL_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // A imagem foi escolhida ou capturada com sucesso
            val imagemSelecionadaUri = data?.data
            // Faça algo com a URI da imagem selecionada, como exibi-la na ImageView
            profile_image?.setImageURI(imagemSelecionadaUri)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditarImagemPerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}