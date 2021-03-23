package com.example.demo_list_tweets.presentation.searchtweets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.demo_list_tweets.R
import com.example.demo_list_tweets.domain.entity.TweetContainer

class SearchTweetsAdapter(
    private val tweets: List<TweetContainer>,
    private val context: Context
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
        //holder.itemView.setOnClickListener { onScooterItemClickedListener.onScooterClicked(getItem(position)) }
        val text: String? = tweets[position].text
        val imageURL: String? = tweets[position].user.profile_image_url_https

        holder.textViewTweetText.text = text
        imageURL?.let {
            Glide.with(context).load(it).apply(
                RequestOptions.bitmapTransform(RoundedCorners(35))).into(holder.imageView);
        }
    }

    override fun getItemCount(): Int = tweets.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTweetText: TextView =
            view.findViewById(R.id.tweetText)

        val imageView: ImageView =
            view.findViewById(R.id.imageView)
    }

    private fun getItem(position: Int): TweetContainer {
        return tweets[position]
    }


//    interface OnScooterItemClickedListener {
//        fun onScooterClicked(vehicle: VehicleDomainModel)
//    }
}
