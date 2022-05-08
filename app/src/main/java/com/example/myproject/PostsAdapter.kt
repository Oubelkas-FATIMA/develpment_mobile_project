package com.example.myproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostsAdapter(val items: List<PostsResult>) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(), Filterable {

    var postFiltredList: List<PostsResult> = ArrayList()

    init {
        postFiltredList = items
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    postFiltredList = items

                } else {
                    var resultList = ArrayList<PostsResult>()
                    for (post in items) {
                        if (post.tags.get(0).lowercase()
                                .contains(charSearch.lowercase()) || post.tags.get(1).lowercase()
                                .contains(charSearch.lowercase()) || post.tags.get(2).lowercase()
                                .contains(charSearch.lowercase())
                        ) {
                            resultList.add(post)
                        }
                    }
                    postFiltredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = postFiltredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                postFiltredList = p1?.values as ArrayList<PostsResult>
                notifyDataSetChanged()
            }

        }
    }

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun getItemCount(): Int {
        return postFiltredList.size
    }

    // 2. Méthode redéfinie qui retourne un ViewHolder et fait une association avec le fichier layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_posts, parent, false)
        return PostsViewHolder(itemView, mListener)

    }
    // 3. Méthode redéfinie qui fait le lien entre les composants et les
    // données à afficher dans le recyclerView

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = postFiltredList[position]
        with(holder) {

            datePost.text = post.publishDate
            userName.text =
                "${post?.owner?.title} ${post?.owner?.firstName} ${post?.owner?.lastName}"
            description.text = post?.text
            tag_0.text = post?.tags?.get(0)
            tag_2.text = post?.tags?.get(1)
            tag_3.text = post?.tags?.get(2)

            Glide.with(itemView)
                .load(post.image)
                .into(imagepost)

            Glide.with(itemView)
                .load(post.owner.picture)
                .into(imageUser)
        }
    }


    class PostsViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        var imageUser: ImageView
        var userName: TextView
        var datePost: TextView
        var imagepost: ImageView
        var description: TextView
        var tag_0: Button
        var tag_2: Button
        var tag_3: Button

        init {
            imageUser = itemView.findViewById(R.id.imageUser)
            userName = itemView.findViewById(R.id.userName)
            datePost = itemView.findViewById(R.id.datePost)
            imagepost = itemView.findViewById(R.id.imagepost)
            tag_0 = itemView.findViewById(R.id.tag_0)
            tag_2 = itemView.findViewById(R.id.tag_2)
            tag_3 = itemView.findViewById(R.id.tag_3)
            description = itemView.findViewById(R.id.description)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


    }


}



