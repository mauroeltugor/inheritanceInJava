package Paquete1;

import javax.swing.JOptionPane;

import clases.CitasMedicas;
import clases.ModeloDatos;
import clases.Paciente;
import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;

public class Procesos {
	ModeloDatos miModeloDatos;

	public Procesos() {
		miModeloDatos = new ModeloDatos();
		presentarMenuOpciones();
	}

	private void presentarMenuOpciones() {

		String menu = "MENU HOSPITAL \n\n";
		menu += "1. Registrar Paciente\n";
		menu += "2. Registrar Empleado\n";
		menu += "3. Registrar Cita Medica\n";
		menu += "4. Imprimir Informacion\n";
		menu += "5. salir \n\n";
		menu += "Ingrese una opcion\n";
        int opcion=0;
		 
		 do {
				opcion= Integer.parseInt(JOptionPane.showInputDialog(menu));
				validacionmenu(opcion);
			} while (opcion!=5);

	}

	private void validacionmenu(int opcion) {
		switch (opcion) {
		case 1:
			registrarPaciente();
			break;
		case 2:
			registrarEmpleado();
			break;
		case 3:
			registrarCitaMedica();
			break;
		case 4:
			imprimirInformacion();
			break;
		case 5:
			System.out.println("el sistema a terminado");
			;
			break;

		default:
			System.out.println("No existe el codigo verifique nuevamente");
			break;
		}
		
	}

	private void imprimirInformacion() {
		// TODO Auto-generated method stub
		String menuImprimir = "MENU IMPRESIONES\n\n";
		menuImprimir += "1. Listar Paciente\n";
		menuImprimir += "2. Listar Empleados Eventuales\n";
		menuImprimir += "3. Listar Empleados Por Planilla\n";
		menuImprimir += "4. Listar Medicos\n";
		menuImprimir += "5. Listar Citas Programadas\n";
		menuImprimir += "6. Salir\n";
		menuImprimir += " Ingrese una opcion\n";
		System.out.println("**************************************");
		int opcion1=0;
		 
		 do {
				opcion1= Integer.parseInt(JOptionPane.showInputDialog(menuImprimir));
				validacionMenuImpresiones(opcion1);
			} while (opcion1!=6);
		
	}
	
	private void validacionMenuImpresiones(int opcion1) {
		switch (opcion1) {
		case 1:
			miModeloDatos.imprimirPaciente();
			break;
		case 2:
			miModeloDatos.imprimirEmpleadosEventuales();
			break;
		case 3:
			miModeloDatos.imprimirEmpleadosPorPlanilla();
			;
			break;
		case 4:
			miModeloDatos.imprimirMedicos();
			break;
		case 5:
			miModeloDatos.imprimirCitasProgramadas();
			break;
		case 6:
			System.out.println("adios");
			break;
		
		default:
			System.out.println("no existe esa opcion");
			break;
		}
	}

	private void registrarCitaMedica() {
		// TODO Auto-generated method stub
		String documentoPaciente = JOptionPane.showInputDialog("ingrese documento del paciente");
		Paciente pacienteEncontrado = miModeloDatos.consultarPacientePorDocumento(documentoPaciente);
		if (pacienteEncontrado != null) {
			String nombreMedico = JOptionPane.showInputDialog("ingrese el nombre del medico");
			Medico medicoEncontrado = miModeloDatos.consultarMedicoPorDocumento(nombreMedico);
			if (medicoEncontrado!=null) {
				String servicio = JOptionPane.showInputDialog("ingrese el motivo de consulta");
				String fechaDeConsulta = JOptionPane.showInputDialog("ingrese fecha de consulta");
				String horaConsulta = JOptionPane.showInputDialog("ingrese hora de consulta");
				
				String lugar="la cita sera en el consultorio "+medicoEncontrado.getNumeroDeConsultorio();
				CitasMedicas miCita= new CitasMedicas(pacienteEncontrado, medicoEncontrado, servicio, fechaDeConsulta, horaConsulta, lugar);
				miModeloDatos.registarCitasMedicas(miCita);

			} else {
                System.out.println("medico no se encuentra registrado");
			}
		} else {
			System.out.println("el paciente no se encuentra registrado");

		}

	}

	private void registrarEmpleado() {
		// TODO Auto-generated method stub
		String tipoEmpleado = "Registro de Empleado\n\n";
		tipoEmpleado += "1. Empleado Eventual\n";
		tipoEmpleado += "2. Empleado por Planilla\n";
		tipoEmpleado += "Seleccione un tipo de empleado a registrar\n";
		int opt = Integer.parseInt(JOptionPane.showInputDialog(tipoEmpleado));
		switch (opt) {
		case 1:
			EmpleadoEventual miEmpleadoEventual = new EmpleadoEventual();
			miEmpleadoEventual.registrarDatos();
			miModeloDatos.registrarPersona(miEmpleadoEventual);
			break;
		case 2:
			EmpleadoPlanilla miEmpledoPlanilla = new EmpleadoPlanilla();
			Medico miMedico = new Medico();
			String menu = "MENU\n\n";
			menu += "1. Medico\n";
			menu += "2. otra clase de empleado\n";
			menu += "Elija una opcion\n";
			int tipomenu = Integer.parseInt(JOptionPane.showInputDialog(menu));
			if (tipomenu == 1) {
				miMedico.registrarDatos();
				miModeloDatos.registrarPersona(miMedico);
			} else if (tipomenu == 2) {
				miEmpledoPlanilla.registrarDatos();
				miModeloDatos.registrarPersona(miEmpledoPlanilla);
			} else {
				System.out.println("valor erroneo");
			}
			break;
		default:
			System.out.println("el tipo de empleado no es valido");
			break;
		}
	}

	private void registrarPaciente() {
		Paciente miPaciente = new Paciente();
		miPaciente.registrarDatos();
		miModeloDatos.registrarPersona(miPaciente);

	}
}
