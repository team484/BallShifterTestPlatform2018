package org.team484.api.sensor;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GyroBase;

public class DoubleGyro extends GyroBase {
	
	private GyroBase top, bottom;
	
	private double offset;
	
	public DoubleGyro(int topPort, int bottomPort) {
		top = new AnalogGyro(topPort);
		bottom = new AnalogGyro(bottomPort);
	}
	
	public DoubleGyro(GyroBase topGyro, GyroBase bottomGyro) {
		top = topGyro;
		bottom = bottomGyro;
	}
	@Override
	public void calibrate() {
		top.calibrate();
		bottom.calibrate();
		
	}

	@Override
	public void reset() {
		top.reset();
		bottom.reset();
	}

	@Override
	public double getAngle() {
		return (top.getAngle() - bottom.getAngle())/2.0;
	}

	@Override
	public double getRate() {
		return (top.getRate() - bottom.getRate())/2.0;
	}
	
	public void applyOffset(double offset) {
		this.offset = offset;
	}
	
	public double getOffsetAngle() {
		return getAngle() + offset;
	}

}
