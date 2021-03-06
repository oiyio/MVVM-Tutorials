package info.androidhive.viewmodel.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.androidhive.viewmodel.R;
import info.androidhive.viewmodel.model.MenuItem;


public class MenuListFragment extends Fragment {

    public static final String TAG = MenuListFragment.class.getSimpleName();

    RecyclerView recyclerView;

    MenuListViewModel viewModel;

    MenuListAdapter mAdapter;

    public MenuListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new MenuListAdapter(getActivity());

        // getting ViewModel instance
        viewModel = ViewModelProviders.of(getActivity()).get(MenuListViewModel.class);

        recyclerView = view.findViewById(R.id.recycler_view);
        // preparing RecyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Observing to list items
        viewModel.getMenuItems().observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(@Nullable List<MenuItem> menuItems) {
                // displaying item in recycler view
                mAdapter.setItems(menuItems);
            }
        });
    }
}
