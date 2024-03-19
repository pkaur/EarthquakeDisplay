import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puneetkaur.testapp.R
import com.puneetkaur.testapp.model.Earthquake

class EarthquakesAdapter(var list: List<Earthquake>) :
    RecyclerView.Adapter<EarthquakesAdapter.EarthQuakesViewHolder>() {

    inner class EarthQuakesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eqid: TextView = itemView.findViewById(R.id.eqid)
        val magnitude: TextView = itemView.findViewById(R.id.magnitude)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakesViewHolder {
        return EarthQuakesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.earthquake, parent, false)
        )
    }

    private var onItemClickListener: ((Earthquake) -> Unit)? = null

    fun setOnClickListener(listener: (Earthquake) -> Unit){
        onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: EarthQuakesViewHolder, position: Int) {
        val result = list[position]

        holder.eqid.text = result.eqid
        holder.magnitude.text = result.magnitude.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(result)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
