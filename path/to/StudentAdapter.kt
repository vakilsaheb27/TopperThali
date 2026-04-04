import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.List

class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)

        holder.itemView.setOnClickListener { 
            // Handle click listener for each item
            // You can define an interface and pass a callback to the adapter to handle click events.
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentName: TextView = itemView.findViewById(R.id.studentName)
        private val studentImage: ImageView = itemView.findViewById(R.id.studentImage)

        fun bind(student: Student) {
            studentName.text = student.name
            // Ensure that the image URL is valid before loading
            if (student.imageUrl.isNotEmpty()) {
                Picasso.get().load(student.imageUrl).into(studentImage)
            }
        }
    }
}