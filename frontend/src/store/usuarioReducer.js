const INITIAL_STATE = {
  usuarioLogado: 0,
  usuarioId: "",
};

function usuarioReducer(state = INITIAL_STATE, action) {
  switch (action.type) {
    case "LOGIN":
      return {
        usuarioLogado: 1,
        userId: action.userId,
        userNome: action.userNome,
        userEmail: action.userEmail,
        userCampus: action.userCampus,
        userCurso: action.userCurso,
      };
    case "LOGOUT":
      return {
        ...state,
        usuarioLogado: 0,
        usuarioId: "",
      };
    case "MATERIA":
      return {
        ...state,
        nomeMateria: action.nomeMateria,
      };
    case "POST":
      return {
        ...state,
        postId: action.postId,
        postPergunta: action.postPergunta,
      };
    default:
      return state;
  }
}

export default usuarioReducer;
