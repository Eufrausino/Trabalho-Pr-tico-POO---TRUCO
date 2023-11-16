package br.ufv.truco;
import java.util.ArrayList;

public class Jogo {
    
    private ArrayList<Mao> maos;
    private Equipe equipeVencedora;

    //Cosntrutor
    public Jogo() {
        this.maos = new ArrayList<Mao>();
        this.equipeVencedora = null;
    } 

    
    //Getters e Setters
    public ArrayList<Mao> getMaos() {
        return this.maos;
    }
    
    public Equipe getEquipeVencedora() {
        return this.equipeVencedora;
    }

    public void setEquipeVencedora(Equipe equipeVencedora) {
        this.equipeVencedora = equipeVencedora;
    }

    public void addMao(Mao mao) {
        this.maos.add(mao);
    }


    //Métodos

    public void executajogo(Equipe equipe1, Equipe equipe2) {
        
        //Enquanto nenhuma equipe tiver 12 pontos, o jogo continua
        while (equipe1.getPontos() < 12 && equipe2.getPontos() < 12) {
            
            //Se alguma equipe tiver 10 pontos, a próxima mão será de valor 6 de padrão (mão de 10/mão de ferro)
            if (equipe1.getPontos() == 10 || equipe2.getPontos() == 10) {
                Mao mao = new Mao();
                mao.setValor(4);
                mao.executaMao();
                this.addMao(mao);
            }
            
            //Executa uma mão de valor 3 de padrão
            else {
                Mao mao = new Mao();
                mao.executaMao();
                this.addMao(mao);
            }
        }



        //Define a equipe vencedora do jogo
        if (equipe1.getPontos() >= 12) {
            this.setEquipeVencedora(equipe1);
        } 

        //Se as duas equipes tiverem 10 pontos, a equipe que estiver trucada vence
        else if (equipe1.getPontos() == 10 || equipe2.getPontos() == 10) {
            
            if (equipe1.getPontos() == 10 && equipe2.estaTrucado() == true){
                this.setEquipeVencedora(equipe2);
            }
            
            else if (equipe2.getPontos() == 10 && equipe1.estaTrucado() == true){
                this.setEquipeVencedora(equipe1);
            }
        }
        
        else {
            this.setEquipeVencedora(equipe2);
        }
    }

    //Define a equipe vencedora do jogo (Printa na tela)
    public void defineVencedor() {
        if (this.getEquipeVencedora() == null) {
            System.out.println("O jogo ainda não foi finalizado");
        } else {
            System.out.println("A equipe vencedora do jogo foi: " + this.getEquipeVencedora().getId());
        }
    }
    
}
