package br.edu.ifba.swso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 * @author Ramon
 */
@Named
@RequestScoped
public class DashboardController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MeterGaugeChartModel meterGaugeModel;

	@Inject
	private MaquinaSessaoController maquinaSessaoController;

	public MaquinaSessaoController getMaquinaSessaoController() {
		return maquinaSessaoController;
	}

	@PostConstruct
	public void init() {
		createMeterGaugeModels();
	}

	public MeterGaugeChartModel getMeterGaugeModel2() {
		return meterGaugeModel;
	}

	private MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>() {
			{
				add(90);
				add(120);
				add(140);
				add(180);
			}
		};

		return new MeterGaugeChartModel(140, intervals);
	}

	private void createMeterGaugeModels() {
		meterGaugeModel = initMeterGaugeModel();
	}

	public MeterGaugeChartModel getMeterGaugeModel() {
		return meterGaugeModel;
	}

	public void setMeterGaugeModel(MeterGaugeChartModel meterGaugeModel) {
		this.meterGaugeModel = meterGaugeModel;
	}

}
