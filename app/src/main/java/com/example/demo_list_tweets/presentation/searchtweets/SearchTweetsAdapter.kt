package com.example.demo_list_tweets.presentation.searchtweets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.demo_list_tweets.R
import com.example.demo_list_tweets.domain.entity.TweetContainer

class SearchTweetsAdapter(
    private val tweets: List<TweetContainer>,
    private val context: Context,
    private val openTweetInBrowser: (url: String) -> Unit
) : RecyclerView.Adapter<SearchTweetsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tweet_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text: String? = tweets[position].text
        val imageURL: String? = tweets[position].user?.profile_image_url_https
        tweets[position].entities?.let { entities ->
            entities.urls?.let { urls ->
                if (urls.count() > 0) {
                    urls[0].expanded_url?.let { url ->
                        if (android.util.Patterns.WEB_URL.matcher(url).matches()) {
                            holder.constraintLayoutRowWrapper.setOnClickListener {
                                openTweetInBrowser(url)
                            }
                        }
                    }
                }
            }
        }

        holder.textViewTweetText.text = text
        imageURL?.let {
            Glide.with(context).load(it).apply(
                RequestOptions.bitmapTransform(RoundedCorners(ROUNDING_PERCENT))
            ).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = tweets.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val constraintLayoutRowWrapper: ConstraintLayout =
            view.findViewById(R.id.rowWrapper)

        val textViewTweetText: TextView =
            view.findViewById(R.id.tweetText)

        val imageView: ImageView =
            view.findViewById(R.id.imageView)
    }

    companion object {
        const val ROUNDING_PERCENT = 100
    }
}
