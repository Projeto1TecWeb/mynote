package mynote;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mynote.Note;

public class DAO {
	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myNote", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Note> getLista() {
		List<Note> notes = new ArrayList<Note>();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM note");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Note note = new Note();
				note.setIdNote(rs.getInt("id_note"));
				note.setNoteText(rs.getString("note_text"));

				note.setTag(rs.getString("tag"));
				note.setIcon(rs.getString("icon"));
				note.setColor(rs.getString("color"));
				note.setIdUser(rs.getInt("id_user"));

				notes.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	public void close() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adiciona(Note note) {
		String sql = "INSERT INTO note" + "(note_text, tag, color, icon, id_user) values(?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, note.getNoteText());
			stmt.setString(2, note.getTag());
			stmt.setString(3, note.getColor());
			stmt.setString(4, note.getIcon());
//			stmt.setInt(5, note.getIdUser());


			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void altera(Note note) {
//		String sql = "UPDATE Pessoas SET " + "nome=?, nascimento=?, altura=? WHERE id=?";
//		PreparedStatement stmt;
//		try {
//			stmt = connection.prepareStatement(sql);
//			stmt.setString(1, note.getNome());
//			stmt.setDate(2, new Date(note.getNascimento().getTimeInMillis()));
//			stmt.setDouble(3, note.getAltura());
//			stmt.setInt(4, note.getId());
//			stmt.execute();
//			stmt.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void remove(Integer id) {
//		PreparedStatement stmt;
//		try {
//			stmt = connection.prepareStatement("DELETE FROM Pessoas WHERE id=?");
//			stmt.setLong(1, id);
//			stmt.execute();
//			stmt.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
}

