package layout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.hypodiabetic.happplus.R;
import com.hypodiabetic.happplus.plugins.PluginBase;

import java.util.List;

/**
 * Created by Tim on 06/01/2017.
 * Simple Adapter showing a list of plugins
 */

public class AdapterPlugins extends RecyclerView.Adapter<AdapterPlugins.PluginViewHolder> {

    public static class PluginViewHolder extends RecyclerView.ViewHolder {

        TextView pluginName;
        TextView pluginDescription;

        ImageView pluginSettings;
        Switch pluginOnOff;

        PluginViewHolder(View itemView) {
            super(itemView);

            pluginName          = (TextView) itemView.findViewById(R.id.pluginName);
            pluginDescription   = (TextView) itemView.findViewById(R.id.pluginDescription);

            pluginSettings      = (ImageView) itemView.findViewById(R.id.pluginSettings);
            pluginOnOff         = (Switch) itemView.findViewById(R.id.pluginOnOff);
        }
    }

    List<PluginBase> plugins;

    public AdapterPlugins(List<PluginBase> plugins) {
        this.plugins = plugins;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public AdapterPlugins.PluginViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_plugin, viewGroup, false);
        PluginViewHolder pvh = new PluginViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AdapterPlugins.PluginViewHolder pluginViewHolder, final int i) {

        pluginViewHolder.pluginName.setText(plugins.get(i).pluginDisplayName);
        pluginViewHolder.pluginDescription.setText(plugins.get(i).pluginDescription);
        pluginViewHolder.pluginOnOff.setChecked(plugins.get(i).isLoaded);
        pluginViewHolder.pluginOnOff.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (plugins.get(i).isLoaded){
                            plugins.get(i).unLoad();
                        } else {
                            plugins.get(i).load();
                        }
                    }
                }
        );
        pluginViewHolder.pluginSettings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );


    }

    @Override
    public int getItemCount() {
        return plugins.size();
    }
}