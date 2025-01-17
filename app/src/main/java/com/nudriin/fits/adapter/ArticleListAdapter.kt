package com.nudriin.fits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nudriin.fits.data.dto.article.ArticleItem
import com.nudriin.fits.databinding.ArticleListCardBinding

class ArticleListAdapter(private val articleList: List<ArticleItem>) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ArticleListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(
                articleId = article.id,
                title = article.title,
                author = article.author,
                content = article.content,
                imgUrl = article.imgUrl,
                date = article.createdAt
            )
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(
            articleId: Int,
            title: String,
            author: String,
            content: String,
            imgUrl: String,
            date: String
        )
    }

    class ViewHolder(private val binding: ArticleListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleItem) {
            with(binding) {
                Glide.with(root.context)
                    .load(article.imgUrl)
                    .into(ivArticleHomeThumbnail)

                tvArticleHomeTitle.text = article.title

                tvArticleHomeDescription.text = article.description
            }
        }

    }
}