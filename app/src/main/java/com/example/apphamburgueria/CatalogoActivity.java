package com.example.apphamburgueria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    private TextView textViewTitulo, textViewCategoria;
    private HorizontalScrollView listaProdutosContainer;
    private Button btnWhatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_produto);

        // Inicializando as views
        textViewTitulo = findViewById(R.id.textView12);
        textViewCategoria = findViewById(R.id.textView10);
        listaProdutosContainer = findViewById(R.id.srvlistaProdutos);
        btnWhatsApp = findViewById(R.id.btnWhatsApp);

        // Configurando o título e a categoria
        textViewTitulo.setText("Hamburgueria dus Guri");
        textViewCategoria.setText("Lanches - Bebidas");

        // Adicionando produtos à lista
        adicionarProdutosNaLista();

        // Configurando o botão do WhatsApp
        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirWhatsApp();
            }
        });
    }

    private void adicionarProdutosNaLista() {
        List<Produto> produtos = getProdutos();

        for (Produto produto : produtos) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_produto, listaProdutosContainer, false);

            ImageView imageViewProduto = itemView.findViewById(R.id.imageView6);
            TextView textViewNome = itemView.findViewById(R.id.textView12);

            imageViewProduto.setImageResource(produto.getImagem());
            textViewNome.setText(produto.getNome());

            listaProdutosContainer.addView(itemView);
        }
    }

    private List<Produto> getProdutos() {
        // Lista de produtos para exibir (exemplo)
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Hamburguer", R.drawable.bk2));
        produtos.add(new Produto("Refrigerante", R.drawable.juice2));
        // Adicione mais produtos conforme necessário
        return produtos;
    }

    private void abrirWhatsApp() {
        String numeroWhatsApp = "559999999999"; // Substitua pelo número de telefone desejado
        String url = "https://wa.me/" + numeroWhatsApp;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}