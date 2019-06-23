package pw.komarov.giphy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.giphy.sdk.core.models.Media;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.GiphyViewHolder> {
    private List<Media> giphyItems;
    RVAdapter(List<Media> giphyItems) {
        this.giphyItems = giphyItems;
    }

    @NonNull
    @Override
    public GiphyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        return new GiphyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GiphyViewHolder giphyViewHolder, int i) {
        giphyViewHolder.tvGiphyId.setText(giphyItems.get(i).getId());
        giphyViewHolder.tvDescription.setText(giphyItems.get(i).getTitle());
        GifImageService.loadProcessingAnimation(giphyViewHolder.imgGif);
    }

    @Override
    public int getItemCount() {
        return giphyItems.size();
    }

    public static class GiphyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDescription;
        private TextView tvGiphyId;
        private GifImageView imgGif;

        GiphyViewHolder(View itemView) {
            super(itemView);

            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvGiphyId = itemView.findViewById(R.id.tvGiphyId);
            imgGif = itemView.findViewById(R.id.imgGif);
        }
    }
}