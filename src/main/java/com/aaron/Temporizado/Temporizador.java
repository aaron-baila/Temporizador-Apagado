package com.aaron.Temporizado;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;

public class Temporizador extends JFrame {

	/**
	 * 
	 * Este programa consiste en activar un apagado automatico a traves del cmd
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSlider slider = new JSlider();
	private boolean temporizadorActivado = false;
	private JTextField txtMinutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temporizador frame = new Temporizador();
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Temporizador() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 144, 0, 0, 0, 81, 0 };
		gbl_contentPane.rowHeights = new int[] { 14, 0, 0, 23, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnCancelar = new JButton("Cancelar temporizado");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});

		JLabel lblNewLabel = new JLabel("TEMPORIZADOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 2;
		slider.setMaximum(200);
		slider.setValue(60);

// actualizar slide
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String valorSlider = slider.getValue() + "";
				txtMinutos.setText(valorSlider);

			}
		});

		JLabel lblNewLabel_1 = new JLabel("Indique el tiempo en minutos: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		contentPane.add(slider, gbc_slider);

		txtMinutos = new JTextField();
		txtMinutos.setText("60");
		txtMinutos.setToolTipText("");
		GridBagConstraints gbc_txtMinutos = new GridBagConstraints();
		gbc_txtMinutos.insets = new Insets(0, 0, 5, 5);
		gbc_txtMinutos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinutos.gridx = 2;
		gbc_txtMinutos.gridy = 2;
		contentPane.add(txtMinutos, gbc_txtMinutos);
		txtMinutos.setColumns(3);
		
				JButton btnTemporizar = new JButton("Comenzar");
				btnTemporizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						apagado();
					}
				});
				
				JLabel lblMinutos = new JLabel("Minutos");
				GridBagConstraints gbc_lblMinutos = new GridBagConstraints();
				gbc_lblMinutos.insets = new Insets(0, 0, 5, 5);
				gbc_lblMinutos.gridx = 3;
				gbc_lblMinutos.gridy = 2;
				contentPane.add(lblMinutos, gbc_lblMinutos);
				GridBagConstraints gbc_btnTemporizar = new GridBagConstraints();
				gbc_btnTemporizar.anchor = GridBagConstraints.WEST;
				gbc_btnTemporizar.fill = GridBagConstraints.VERTICAL;
				gbc_btnTemporizar.insets = new Insets(0, 0, 5, 0);
				gbc_btnTemporizar.gridx = 4;
				gbc_btnTemporizar.gridy = 2;
				contentPane.add(btnTemporizar, gbc_btnTemporizar);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridwidth = 5;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 3;
		contentPane.add(btnCancelar, gbc_btnCancelar);

	}

	public boolean pruebaError() {
		boolean error = false;
		try {
			@SuppressWarnings("unused")
			// int minutos = Integer.parseInt(txtMinutos.getText());
			int minutos = 9;

			if (!txtMinutos.getText().isBlank()) {
				minutos = Integer.parseInt(txtMinutos.getText());
			}

		} catch (Exception e) {
			error = true;
			JOptionPane.showMessageDialog(this, "Introduzca solo numeros enteros por favor", "error",
					JOptionPane.ERROR_MESSAGE);
		}
		return error;
	}

	public void cancelar() {
		JOptionPane.showMessageDialog(this, "Temporizado cancelado");
		// apagado(!temporizadorActivado);
		String cmd = "shutdown /a";
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
		}
	}

	public long transformarMinutosSegundos(int minutos) {
		long segundos = minutos * 60;
		return segundos;
	}

	public void apagado() {
		if (!pruebaError()) {
			int minutos = Integer.parseInt(txtMinutos.getText());
			// Los minutos no pueden ser menos de 5
			if (minutos < 5) {
				JOptionPane.showMessageDialog(this, "El minimo son 5 minutos", "error", JOptionPane.ERROR_MESSAGE);
			} else {
				long segundos = transformarMinutosSegundos(minutos);

				try {
					String cmd = "shutdown /a";
					cmd = "shutdown -s -t " + segundos;
					Runtime.getRuntime().exec(cmd);
					JOptionPane.showMessageDialog(this, "El ordenador se apagara en " + minutos + " minutos", "TIEMPO", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}


}
