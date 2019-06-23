package pw.komarov.giphy;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GiphyAdapter extends RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder> {

    private static byte[] processingImageBytes;
//    static {
//        Context ctx = GiphyApplication.getAppContext();
//        Resources res = GiphyApplication.getAppContext().getResources();//giphyViewHolder.imgGif.getContext().getResources();
//        InputStream inputStream = res.openRawResource(R.raw.processing);
//        try {
//            processingImageBytes = IOUtils.toByteArray(inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    @NonNull
    @Override
    public GiphyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        GiphyViewHolder giphyViewHolder = new GiphyViewHolder(v);

        return giphyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GiphyViewHolder giphyViewHolder, int i) {
        if(processingImageBytes == null) {
            Resources res = giphyViewHolder.imgGif.getContext().getResources();
            InputStream inputStream = res.openRawResource(R.raw.processing);
            try {
                processingImageBytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        giphyViewHolder.tvDescription.setText(giphyItems.get(i).description);
        giphyViewHolder.tvGiphyId.setText(giphyItems.get(i).imageId);

        giphyViewHolder.imgGif.setBytes(processingImageBytes);
        giphyViewHolder.imgGif.startAnimation();
    }

    @Override
    public int getItemCount() {
        return giphyItems.size();
    }

    public static class GiphyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvDescription;
        TextView tvGiphyId;
        GifImageView imgGif;

        GiphyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvGiphyId = itemView.findViewById(R.id.tvGiphyId);
            imgGif = itemView.findViewById(R.id.imgGif);
        }
    }

    private List<GiphyItem> giphyItems;

    GiphyAdapter(List<GiphyItem> giphyItems) {
        this.giphyItems = giphyItems;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}