/*
 * Copyright (c) 2017
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package jsettlers.main.android.gameplay.ui.fragments.menus.selection.features;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jsettlers.common.buildings.IBuilding;
import jsettlers.graphics.map.controls.original.panel.selection.BuildingState;
import jsettlers.logic.buildings.trading.TradingBuilding;
import jsettlers.main.android.R;
import jsettlers.main.android.core.controls.ActionControls;
import jsettlers.main.android.core.controls.DrawControls;
import jsettlers.main.android.core.controls.DrawListener;
import jsettlers.main.android.gameplay.navigation.MenuNavigator;
import jsettlers.main.android.gameplay.ui.customviews.InGameButton;
import jsettlers.main.android.utils.OriginalImageProvider;

/**
 * Created by Rudolf Polzer
 */
public class TradingFeature extends SelectionFeature implements DrawListener {

    private static final String imageWaypointsLand = "original_3_GUI_374";
    private static final String imageWaypointsSea = "original_3_GUI_377";

    private final ActionControls actionControls;
    private final DrawControls drawControls;

    private RecyclerView materialView;
//    private MaterialsAdapter adapter;

    public TradingFeature(View view, IBuilding building, MenuNavigator menuNavigator, DrawControls drawControls, ActionControls actionControls) {
        super(view, building, menuNavigator);
        this.actionControls = actionControls;
        this.drawControls = drawControls;
    }

    @Override
    public void initialize(BuildingState buildingState) {
        super.initialize(buildingState);

        InGameButton waypointsButton = (InGameButton) getView().findViewById(R.id.image_view_waypoints);
        if (((TradingBuilding) (this.getBuilding())).isSeaTrading()) {
            OriginalImageProvider.get(imageWaypointsSea).setAsImage(waypointsButton.getImageView());
        } else {
            OriginalImageProvider.get(imageWaypointsLand).setAsImage(waypointsButton.getImageView());
        }

        materialView = (RecyclerView) getView().findViewById(R.id.recycler_view_materials);
/*
        if (adapter == null) {
            adapter = new BuildingsCategoryFragment.BuildingsAdapter(buildingTiles);
        } else {
            adapter.setBuildingTiles(buildingTiles);
        }

        if (recyclerView.getAdapter() == null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recyclerView.setAdapter(adapter);
        }
*/
        update();
        drawControls.addInfrequentDrawListener(this);
    }

    @Override
    public void finish() {
        super.finish();
        drawControls.removeInfrequentDrawListener(this);
    }

    @Override
    public void draw() {
        if (hasNewState()) {
            getView().post(() -> update());
        }
    }

    private void update() {
    }

}