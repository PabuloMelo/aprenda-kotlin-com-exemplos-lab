
//Classe responsavel por definir o nivel do curso e formação
enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

//Classe usuario responsavel por definir os dados atribuidos ao usuario
data class Usuario(val nome: String, val email: String)

//classe de conteudos responsavel por seus atributos como nome, duração e nivel
data class ConteudoEducacional(val nome: String, val dificuldade: Nivel){
    
    //define a carga horaria de acordo com o nivel do curso
    
    val duracao: Int
    
    get() = when (dificuldade){
        
        Nivel.BASICO -> 60
        Nivel.INTERMEDIARIO -> 280
        Nivel.AVANCADO -> 360
        
    }
    
} 
    

    //classe responsavel por definir a formação e seus atributos

data class Formacao(val nome: String, val conteudos: MutableList<ConteudoEducacional>, val nivel: Nivel) {

    //variavel responsavel por listar os Usuarios inscritos na formação
    val inscritos = mutableListOf<Usuario>()
    
    //função responsavel por inserir os usuarios na lista de inscritos na formação
    
    fun matricular(usuario: Usuario) {
        if (usuario !in inscritos){
           
            inscritos.add(usuario)
            println("\nUsuario ${usuario.nome} matriculado na formação $nome de nivel: $nivel")
        }else println("\nUsuario ${usuario.nome} já está inscrito nesta formação") 
    }
    
    //função responsavel por atribuir a carga horaria de cada curso na hora da impressão
    override fun toString(): String {
        val conteudosString = conteudos.joinToString("\n") { "${it.nome} - Duração: ${it.duracao} minutos" }
        return """
            |Formação: $nome
            |Nível: $nivel
            |Conteúdos:
            |$conteudosString
        """.trimMargin()
    }
}

fun main() {
    
    //criação de usuarios para realizar os teste de inserção a classe usuario
    val usuario1 = Usuario("Pabulo","pabulo@gmail.com")
    val usuario2 = Usuario("Cleiton Rasta", "cleito@gmail.com")
    val usuario3 = Usuario("zezim", "ZezimDoPneu@gmail.com")
    
    //criação de conteudos para realizar os teste de inserção a classe contetudos
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Versionamento de codigo Git e GitHub", Nivel.BASICO)
    val conteudo3 = ConteudoEducacional("Introdução a Banco de Dados Relacionais SQL", Nivel.INTERMEDIARIO)
    val conteudo4 = ConteudoEducacional("Introdução a Banco de Dados NÃO Relacionais NoSQL", Nivel.INTERMEDIARIO)
    val conteudo5 = ConteudoEducacional("Introdução a SpringBoot", Nivel.AVANCADO)
    val conteudo6 = ConteudoEducacional("Introdução a SpringBootWeb", Nivel.AVANCADO)
    
    //criação da formação com os parametros desejados
    val formacao = Formacao("Formação em Kotling",mutableListOf(conteudo1,conteudo2,conteudo3,conteudo4), Nivel.INTERMEDIARIO)
    
    println("\n$formacao")
    //matricula 2 usuarios a formação
    formacao.matricular(usuario2)
    formacao.matricular(usuario3)
    //lista a formação e seus respectivos inscritos
    println("\nInscritos em ${formacao.nome} apos a matricula: ${formacao.inscritos}")
   
   //adiciona novos conteudos a formação
    formacao.conteudos.add(conteudo5)
    formacao.conteudos.add(conteudo6)
    
    println("\n$formacao")
    
    //adiciona um novo usuario a lista de inscritos
    formacao.matricular(usuario1)
  
    //reimpressão da nova lista de usuarios e dos novos conteudos adicionados
    println("\nInscritos em ${formacao.nome} apos a matricula: ${formacao.inscritos}")
    
   
    
}