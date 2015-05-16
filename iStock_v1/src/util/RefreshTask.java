package util;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;

public class RefreshTask {
	private Display display;

	public RefreshTask(Display _dDisplay) {
		display = _dDisplay;
	}

	public void scheduleRecordChangeRf() {
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// OwnershipTabItemComposite.redrawui();
				// ownershiptabOnPrerio.setSignal(false);
				// wealTabItemComposite.redrawui();
				// wealtabOnPrerio.setSignal(false);
				try {
					Constant.RecordChangeRefresh.refreshAndSave();
					System.out.println("asyncExec");
				} catch (SWTException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Timer schedulePreriodicRf() {

		Timer t1 = new java.util.Timer();

		PreRefreshTask task = new PreRefreshTask(180000);
		t1.schedule(task, 180000, 180000);
		return t1;
	}

	private class PreRefreshTask extends TimerTask {
		private int timerInterval;

		public PreRefreshTask(int timeInterval) {
			this.timerInterval = timeInterval;
		}

		public void run() {
			// 在这里添加你需要周期性运行的代码
			display.asyncExec(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// OwnershipTabItemComposite.redrawui();
					// ownershiptabOnPrerio.setSignal(false);
					// wealTabItemComposite.redrawui();
					// wealtabOnPrerio.setSignal(false);
					try {
						Constant.PreriodicRefresh.refreshAndSave();
						System.out.println("asyncExec");
					} catch (SWTException e) {
						e.printStackTrace();
					}
				}
			});

		}

	}

}
