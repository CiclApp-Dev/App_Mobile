import adaptador_inventario.ViewHolderTest
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ciclapp.Item_de_inventario
import com.example.ciclapp.R
import java.util.*

class adaptador_inventario(private val mItemDeLaLista: ArrayList<Item_de_inventario>) :
    RecyclerView.Adapter<ViewHolderTest>() {
    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }



    class ViewHolderTest(
        itemView: View,
        listener: OnItemClickListener?
    ) :
        RecyclerView.ViewHolder(itemView) {
        var mTextView1: TextView
        var mTextView2: TextView
        var mTextView3: TextView
        var mTextView4: TextView
        var mTextView5: TextView
        var mButton1: Button
        var mButton2: Button

        init {
            mTextView1 = itemView.findViewById(R.id.MarcaDelTelefono)
            mTextView2 = itemView.findViewById(R.id.ModeloDelTelefono)
            mTextView3 = itemView.findViewById(R.id.Reparabilidad)
            mTextView4 = itemView.findViewById(R.id.Color)
            mTextView5 = itemView.findViewById(R.id.Cantidad)
            mButton1 = itemView.findViewById(R.id.boton_sumar)
            mButton2 = itemView.findViewById(R.id.boton_restar)
            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTest {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_inventario, parent, false
        )

        return ViewHolderTest(v, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolderTest, position: Int) {
        val currentItem = mItemDeLaLista[position]
        holder.mTextView1.text = currentItem.getmarcaTelefono()
        holder.mTextView2.text = currentItem.getmodeloTelefono()
        holder.mTextView3.text = currentItem.getRepuesto()
        holder.mTextView4.text = currentItem.getColor()
        holder.mTextView5.text = currentItem.getCantidad()

        holder.mButton1.setOnClickListener {
            val nueva_cantidad = (currentItem.getCantidad().toInt() + 1).toString()
            currentItem.setCantidad(nueva_cantidad)
            holder.mTextView5.text = nueva_cantidad
        }
        holder.mButton2.setOnClickListener {
            val nueva_cantidad = (currentItem.getCantidad().toInt() - 1).toString()
            currentItem.setCantidad(nueva_cantidad)
            holder.mTextView5.text = nueva_cantidad
        }
    }

    override fun getItemCount(): Int {
        return mItemDeLaLista.size
    }



}