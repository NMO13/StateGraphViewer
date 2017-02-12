package at.jku.ssw.zestexample;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ZestTreeExamplePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "at.jku.ssw.zestexample";

	// The shared instance
	private static ZestTreeExamplePlugin plugin;

	/**
	 * The constructor
	 */
	public ZestTreeExamplePlugin() {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ZestTreeExamplePlugin getDefault() {

		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(final String path) {

		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
