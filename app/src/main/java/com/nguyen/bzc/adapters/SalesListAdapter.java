package com.nguyen.bzc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.nguyen.bzc.R;

import java.util.ArrayList;
import java.util.Arrays;


public class SalesListAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflator;
    //private ViewHolder viewHolder;
    private final ArrayList<String> listForData;
    private ArrayList<String> filteredList;

    private LanguageFilter languageFilter;

    private int indexOfSide = 1; //1 is left, 2 is right

    public SalesListAdapter(Context context, int resource, String[] s, int indexOfSide) {
        super(context, resource);
        listForData = new ArrayList<>(Arrays.asList(s));
        this.indexOfSide = indexOfSide;

        this.filteredList = listForData;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public String getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        // General ListView optimization code.
        if (view == null) {
            view = mInflator.inflate(R.layout.notes_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewName = (TextView) view.findViewById(R.id.sale_name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String selectedSale = getItem(i);

        viewHolder.textViewName.setText(selectedSale);

        return view;
    }

    @Override
    public Filter getFilter() {
        if (languageFilter == null) {
            languageFilter = new LanguageFilter();
        }

        return languageFilter;
    }


    private class ViewHolder {
        //ImageView imageViewFlagCountry;
        TextView textViewName;

    }


    private class LanguageFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<String> tempList = new ArrayList<>();

                // search content in notes list
                for (String user : listForData) {
                    if (user.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(user);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = listForData.size();
                filterResults.values = listForData;
            }

            return filterResults;
        }


        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}

