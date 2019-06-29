package pw.komarov.giphy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.giphy.sdk.core.models.Media;

import java.io.UnsupportedEncodingException;
import java.util.List;

import pw.komarov.giphy.utils.GiphyService;
import pw.komarov.giphy.utils.HttpAsyncTask;

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
        final String id = giphyItems.get(i).getId();
        final String url = GiphyService.getUrlById(id);
        final GifImageView img = giphyViewHolder.imgGif;

        giphyViewHolder.tvGiphyId.setText(id);
        giphyViewHolder.tvDescription.setText(giphyItems.get(i).getTitle());
        giphyViewHolder.imgGif.setBytes(GiphyApp.getProcessingAnimationBytes());
        giphyViewHolder.imgGif.startAnimation();

        new HttpAsyncTask() {
            @Override
            protected void onPostExecute(final byte[] bytes) {
                try {
                    String signature = new String(bytes, "ANSI-1251").substring(0, 3);
                    if (signature == "GIF")
                        Log.d("onPostExecute()", "Signature 1..3 bytes IS " + signature);
                    else
                        Log.w("onPostExecute()[e.1]", "Code !!!" + signature.toString() + "!!!");
                } catch (UnsupportedEncodingException e) {
                    Log.e("onPostExecute()", e.getStackTrace().toString());
                }

                img.setBytes(bytes);
                img.startAnimation();
            }
        }.execute(url);
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