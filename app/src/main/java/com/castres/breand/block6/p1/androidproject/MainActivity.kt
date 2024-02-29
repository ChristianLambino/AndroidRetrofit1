    package com.castres.breand.block6.p1.androidproject

    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.LinearSnapHelper
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.SnapHelper

    class MainActivity : AppCompatActivity() {

        //start of new arrivals

        private lateinit var recyclerView: RecyclerView
        private lateinit var newArrivalsList: ArrayList<NewArrivals>
        private lateinit var newArrivalsAdapter :NewArrivalsAdapter

        //start of components
        private lateinit var recyclerView1: RecyclerView
        private lateinit var componentsList: ArrayList<Components>
        private lateinit var componentsAdapter: ComponentsAdapter

        //start of components
        private lateinit var recyclerView2: RecyclerView
        private lateinit var psList: ArrayList<Partnerships>
        private lateinit var psAdapter: PartnershipsAdapter


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            init()

        }


        private fun init() {
            // new arrivals
            recyclerView = findViewById(R.id.recyclerView) ?: return // Return early if recyclerView is null
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            newArrivalsList = ArrayList()

            addDataToList()

            newArrivalsAdapter = NewArrivalsAdapter(newArrivalsList)
            recyclerView.adapter = newArrivalsAdapter

            // components
            recyclerView1 = findViewById(R.id.recyclerView2)
            recyclerView1.setHasFixedSize(true)
            recyclerView1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper1: SnapHelper = LinearSnapHelper()
            snapHelper1.attachToRecyclerView(recyclerView1)
            componentsList = ArrayList()

            addDataToList2()

            componentsAdapter = ComponentsAdapter(componentsList)
            recyclerView1.adapter = componentsAdapter

            // partnerships
            recyclerView2 = findViewById(R.id.recyclerView3)
            recyclerView2.setHasFixedSize(true)
            recyclerView2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val snapHelper2: SnapHelper = LinearSnapHelper()
            snapHelper2.attachToRecyclerView(recyclerView2)
            psList = ArrayList()

            addDataToList3()

            psAdapter = PartnershipsAdapter(psList)
            recyclerView2.adapter = psAdapter
        }

        private fun addDataToList() {
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item1"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item2"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item3"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item4"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item5"))
            newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item6"))
        }


        private fun addDataToList2 (){
            componentsList.add(Components(R.drawable.csd_logo, "Item1"))
            componentsList.add(Components(R.drawable.csd_logo, "Item2"))
            componentsList.add(Components(R.drawable.csd_logo, "Item3"))
            componentsList.add(Components(R.drawable.csd_logo, "Item4"))
            componentsList.add(Components(R.drawable.csd_logo, "Item5"))
            componentsList.add(Components(R.drawable.csd_logo, "Item6"))

            // Remove these lines as recyclerView2 is already initialized in onCreate
            // recyclerView2 = findViewById(R.id.recyclerView3)
            // recyclerView2.setHasFixedSize(true)
            // recyclerView2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false )

            val snapHelper : SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView2) // Use recyclerView2 instead of recyclerView

            psList = ArrayList()

            addDataToList3()

            psAdapter = PartnershipsAdapter(psList)
            recyclerView2.adapter = psAdapter // Set the adapter to recyclerView2 instead of recyclerView

        }

        private fun addDataToList3 () {
            psList.add(Partnerships(R.drawable.csd_logo, "Item1"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item2"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item3"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item4"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item5"))
            psList.add(Partnerships(R.drawable.csd_logo, "Item6"))
        }

    }