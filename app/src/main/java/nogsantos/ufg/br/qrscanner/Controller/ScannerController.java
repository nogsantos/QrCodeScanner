package nogsantos.ufg.br.qrscanner.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nogsantos.ufg.br.qrscanner.Model.ScannerModel;
import nogsantos.ufg.br.qrscanner.R;

/**
 * Created by nogsantos on 8/23/14.
 */
public class ScannerController extends BaseAdapter {
    private Context context;

    private List<ScannerModel> lstScanner;
    private LayoutInflater inflater;

    public ScannerController(Context context, List<ScannerModel> listScanner) {
        this.context    = context;
        this.lstScanner = listScanner;
        inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /*
     * Atualizar ListView de acordo com o lstContato
     */
    @Override
    public void notifyDataSetChanged() {
        try{
            super.notifyDataSetChanged();
        }catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show ();
            e.printStackTrace();
        }
    }
    /**
     *
     */
    public int getCount() {
        return lstScanner.size();
    }
    /**
     * Remover item da lista
     */
    public void remove(final ScannerModel item) {
        this.lstScanner.remove(item);
    }
    /**
     * Adicionar item na lista
     */
    public void add(final ScannerModel item) {
        this.lstScanner.add(item);
    }
    /**
     *
     */
    public Object getItem(int position) {
        return lstScanner.get(position);
    }
    /**
     *
     */
    public long getItemId(int position) {
        return position;
    }
    /**
     *
     */
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        try{
            ScannerModel contato = lstScanner.get(position);
            /*
             * O ViewHolder irá guardar a instâncias dos objetos do estado_row
             */
            ViewHolder holder;
            /*
             * Quando o objeto convertView não for nulo nós não precisaremos inflar
             * os objetos do XML, ele será nulo quando for a primeira vez que for carregado
             */
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.scann_row, null);
                /*
                 * Cria o Viewholder e guarda a instância dos objetos
                 */
                holder         = new ViewHolder();
                holder.txtText = (TextView) convertView.findViewById(R.id.txtText);
                holder.txtData = (TextView) convertView.findViewById(R.id.txtData);

                convertView.setTag(holder);
            } else {
                /*
                 * pega o ViewHolder para ter um acesso rápido aos objetos do XML
                 * ele sempre passará por aqui quando,por exemplo, for efetuado uma rolagem na tela
                 */
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txtText.setText(contato.getText());
            holder.txtData.setText(Long.toString(contato.getDate()));

            return convertView;

        }catch (Exception e) {
            Toast.makeText (context, e.getMessage(), Toast.LENGTH_SHORT).show ();
            e.printStackTrace();
        }
        return convertView;
    }
    /**
     * Criada esta classe estática para guardar a referência dos objetos abaixo
     */
    static class ViewHolder {
        public TextView txtText;
        public TextView txtData;
    }
}
