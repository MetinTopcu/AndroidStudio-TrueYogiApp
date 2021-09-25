package com.metin.firebaselogindeneme.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metin.firebaselogindeneme.R;
import com.metin.firebaselogindeneme.models.MoodHistory;

import java.util.List;

public class MoodHistoryAdapter extends RecyclerView.Adapter<MoodHistoryAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<MoodHistory> moodHistoryList;
    //private Veritabani vt;
    //private static int sayac = 9;

    public MoodHistoryAdapter(Context mContext, List<MoodHistory> moodHistoryList) {
        this.mContext = mContext;
        this.moodHistoryList = moodHistoryList;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        final MoodHistory mood = moodHistoryList.get(position);

        holder.textViewDate.setText(mood.getMoodselecteddate());
        holder.textViewTitle.setText(mood.getMoodcode());

        holder.mood_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, MoodHistoryDetailActivity.class);
                //intent.putExtra("nesne", mood);
                //mContext.startActivity(intent);
                //vt = new Veritabani(mContext);
                //MoodHistory moodHistory = new MoodHistory();
                //sayac++;
                //moodHistory.notSil(vt,sayac);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moodHistoryList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewDate;
        private TextView textViewTitle;
        private CardView mood_card;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            mood_card = itemView.findViewById(R.id.mood_card);
        }

    }
}
