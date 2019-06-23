package pw.komarov.giphy;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giphy.sdk.core.models.Media;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.GiphyViewHolder> {

    private static byte[] processingImageBytes;
    @NonNull
    @Override
    public GiphyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        return new GiphyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GiphyViewHolder giphyViewHolder, int i) {
        if(processingImageBytes == null) { //ToDo: Make it static by app ctx
            Resources res = giphyViewHolder.imgGif.getContext().getResources();
            InputStream inputStream = res.openRawResource(R.raw.processing);
            try {
                processingImageBytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        giphyViewHolder.tvGiphyId.setText(giphyItems.get(i).getId());
        giphyViewHolder.tvDescription.setText(giphyItems.get(i).getTitle());

        //show "processing" animation
        giphyViewHolder.imgGif.setBytes(processingImageBytes);
        giphyViewHolder.imgGif.startAnimation();
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

    private List<Media> giphyItems;

    RVAdapter(List<Media> giphyItems) {
        this.giphyItems = giphyItems;
    }
}