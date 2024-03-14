    package com.castres.breand.block6.p1.androidproject

    import android.content.Intent
    import android.os.Bundle
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.LinearSnapHelper
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.SnapHelper
    import com.castres.breand.block6.p1.androidproject.Components.ComponentsActivity
    import com.castres.breand.block6.p1.androidproject.Components.ComponentsAdapter
    import com.castres.breand.block6.p1.androidproject.Components.ComponentsItems

    class MainActivity : AppCompatActivity(), NewArrClickListener {

        //start of new arrivals

        private lateinit var recyclerView: RecyclerView
        private lateinit var newArrivalsList: ArrayList<NewArrivals>
        private lateinit var newArrivalsAdapter :NewArrivalsAdapter

        //start of components
        private lateinit var recyclerView1: RecyclerView
        private lateinit var componentsList: MutableList<ComponentsItems>
        private lateinit var componentsAdapter: ComponentsAdapter

        //start of components
        private lateinit var recyclerView2: RecyclerView
        private lateinit var psList: ArrayList<Partnerships>
        private lateinit var psAdapter: PartnershipsAdapter



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            initNewArrivals()
            initComponents()
            initPartnerships()

            val redirectsToNewArrivalsActivity : TextView = findViewById(R.id.tvNewArrivals)

            redirectsToNewArrivalsActivity.setOnClickListener{
                val intent = Intent(this@MainActivity, NewArrivalsActivity::class.java)
                startActivity(intent)
            }

            val redirectsToComponentsActivity : TextView = findViewById(R.id.tvComponents)

            redirectsToComponentsActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, ComponentsActivity::class.java)
                startActivity(intent)
            }

            val redirectsToPartnershipsActivity : TextView = findViewById(R.id.tvPartnerships)

            redirectsToPartnershipsActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, PartnershipsActivity::class.java)
                startActivity(intent)
            }



        }

        private fun initNewArrivals() {
            // new arrivals
            recyclerView = findViewById(R.id.recyclerView) ?: return // Return early if recyclerView is null
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            newArrivalsList = ArrayList()

            addDataToNewArrivalsList()

            newArrivalsAdapter = NewArrivalsAdapter(newArrivalsList)
            recyclerView.adapter = newArrivalsAdapter
        }


        private fun addDataToNewArrivalsList() {
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item1"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item2"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item3"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item4"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item5"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item6"))
        }
        private fun initComponents() {
            recyclerView1 = findViewById(R.id.recyclerView2)
            recyclerView1.setHasFixedSize(true)
            recyclerView1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper1: SnapHelper = LinearSnapHelper()
            snapHelper1.attachToRecyclerView(recyclerView1)
            componentsList = ArrayList()

            addDataToComponentsList()

            componentsAdapter = ComponentsAdapter(componentsList)
            recyclerView1.adapter = componentsAdapter
        }

        private fun addDataToComponentsList() {
            componentsList.add(ComponentsItems(R.drawable.csd_logo, "Item1", "", 123.0, "GPU", "LOREM"))
            componentsList.add(ComponentsItems(R.drawable.csd_logo, "Item2", "", 123.0, "GPU", "LOREM"))
            componentsList.add(ComponentsItems(R.drawable.csd_logo, "Item3", "", 123.0, "GPU", "LOREM"))
            // Add more items as needed
        }





        private fun initPartnerships(){
            recyclerView2 = findViewById(R.id.recyclerView3)
            recyclerView2.setHasFixedSize(true)
            recyclerView2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper2: SnapHelper = LinearSnapHelper()
            snapHelper2.attachToRecyclerView(recyclerView2)
            psList = ArrayList()

            addDataToPartnershipsList()

            psAdapter = PartnershipsAdapter(psList)
            recyclerView2.adapter = psAdapter
        }

        private fun addDataToPartnershipsList () {
            psList.add(Partnerships(R.drawable.csd_logo, "Item1"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item2"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item3"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item4"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item5"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item6"))
        }

        override fun onClick(newArrItems: NewArrItems) {
            val intent = Intent(applicationContext, NewArrivalsDetailActivity::class.java)
            intent.putExtra(NEW_ARR_ID_EXTRA, newArrItems.id)
            startActivity(intent)

        }

    }