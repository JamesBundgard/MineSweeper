package com.example.minesweeper

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numColumns = 10
        val numRows = 10
        val itemList = Array(numColumns * numRows) {i -> i.toString()}

        val adapter = ImageListAdapter(this, R.layout.tile, itemList)
        val gameGrid: GridView = findViewById(R.id.grid)

        gameGrid.numColumns = numColumns
        gameGrid.columnWidth = GridView.AUTO_FIT
        gameGrid.stretchMode = GridView.STRETCH_COLUMN_WIDTH
        gameGrid.adapter = adapter

        gameGrid.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            println(parent.toString() + position.toString() + v.toString() + id.toString())
        }

    }
}

internal class ImageListAdapter internal constructor(context: Context, private val resource: Int, private val itemList: Array<String>?) : ArrayAdapter<ImageListAdapter.ItemHolder>(context, resource) {

    override fun getCount(): Int = if (this.itemList != null) this.itemList.size else 0

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val holder: ItemHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()
            holder.name = convertView!!.findViewById(R.id.tile)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

        holder.name!!.text = this.itemList!![position]

        return convertView
    }

    internal class ItemHolder {
        var name: TextView? = null
    }
}


