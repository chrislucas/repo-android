package com.br.ar.view;

import java.util.ArrayList;

import com.beyondar.android.world.BeyondarObject;

/**
 * On click listener to detect when a
 * {@link BeyondarObject BeyondarObject} has been
 * clicked on the {@link com.beyondar.android.view.BeyondarGLSurfaceView
 * BeyondarGLSurfaceView}.
 */
public interface OnClickBeyondarObjectListener {

	/**
	 * This method is called when the user click on a {@link BeyondarObject BeyondarObject}
	 * 
	 * @param beyondarObjects
	 *            All the {@link BeyondarObject BeyondarObject} that collide with the ray
	 *            generated by the user click. If no object have been clicked
	 *            the {@link ArrayList} will be empty
	 */
	public void onClickBeyondarObject(ArrayList<BeyondarObject> beyondarObjects);

}