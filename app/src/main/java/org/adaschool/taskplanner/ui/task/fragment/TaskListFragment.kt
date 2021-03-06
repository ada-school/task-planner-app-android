package org.adaschool.taskplanner.ui.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.adaschool.taskplanner.R
import org.adaschool.taskplanner.data.entity.Task
import org.adaschool.taskplanner.databinding.FragmentTaskListBinding
import org.adaschool.taskplanner.ui.task.adapter.TaskItemClickListener
import org.adaschool.taskplanner.ui.task.adapter.TasksAdapter
import org.adaschool.taskplanner.ui.task.viewmodel.TaskViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class TaskListFragment : Fragment(), TaskItemClickListener {

    private var _binding: FragmentTaskListBinding? = null

    private val viewModel: TaskViewModel by activityViewModels()

    private val binding get() = _binding!!

    private val adapter = TasksAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        subscribeObservers()
        configureSwipeToRefresh()
    }

    private fun configureSwipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            viewModel.syncTasks()
            binding.swiperefresh.isRefreshing = false
        }
    }

    private fun subscribeObservers() {
        viewModel.taskListLiveData.observe(viewLifecycleOwner, {
            adapter.updateTasksList(it)
        })
    }

    private fun configureRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTaskClicked(task: Task) {
        viewModel.selectedTask = task
        findNavController().navigate(R.id.action_TaskListFragment_to_TaskFormFragment)
    }
}