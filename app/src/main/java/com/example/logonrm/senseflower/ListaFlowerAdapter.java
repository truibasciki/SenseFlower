package com.example.logonrm.senseflower;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.logonrm.senseflower.model.Flower;

import java.util.List;

/**
 * Created by asus on 01/04/2017.
 */

public class ListaFlowerAdapter extends ArrayAdapter<Flower> {

        List<Flower> flowers;
        Context context;

        public ListaFlowerAdapter(Context context, List<Flower> objects) {
            super(context, R.layout.linha_list_view, objects);
            this.flowers = objects;
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //Preencher da forma que está no item_aluno.xml as informações
            final Flower flower = flowers.get(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.linha_list_view, parent, false);

            final Button btnInformacao = (Button) v.findViewById(R.id.btnInformacoes);

            String[] flores = context.getResources().getStringArray(R.array.flores);
            btnInformacao.setText(flores[((int) flower.getTipo())]);
            btnInformacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsFlowerActivity.class);
                    if (flower != null) {
                        intent.putExtra("id", flower.getId());
                    }
                    //Bundle bundle = new Bundle();
                    //bundle.putString("turma", turma.getText);
                    // intent.putExtras(bundle);

                    context.startActivity(intent);
                }
            });

            return v;
        }

}
