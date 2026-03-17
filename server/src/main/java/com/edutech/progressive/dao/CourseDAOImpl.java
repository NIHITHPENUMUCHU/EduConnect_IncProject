@Override
public int addCourse(Course course) throws SQLException {
    String sql = "INSERT INTO course (course_name, description, teacher_id) VALUES (?, ?, ?)";

    try (Connection con = DatabaseConnectionManager.getConnection();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, course.getCourseName());
        ps.setString(2, course.getDescription());

        if (course.getTeacherId() == 0) {
            ps.setNull(3, Types.INTEGER);
        } else {
            ps.setInt(3, course.getTeacherId());
        }

        int result = ps.executeUpdate();
        if (result == 0) return -1;

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }
}