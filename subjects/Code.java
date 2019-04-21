package subjects;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Code{

    List<Card> cards = new ArrayList<>();
    public static LocalDate today = LocalDate.parse("19.03.2019", DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    public Map<Card,Cost> getCardsCost(){
        Map<Card,Cost> ret = new HashMap<Card,Cost>();
        List<Card> crds = new ArrayList<>();
        //copy the valid cards
        for(Card c: cards){
            if(c.isValid(today)){
                crds.add(c);
            }
        }
        // sort the cards by fee (ascending order)
        crds.sort((x,y)->{return (int)(x.getFee()*100 - y.getFee()*100);});

        //TVA is always the same, because the sum that we want to pay remains the same
        double TVA = 10000d * 19d / 100d;

        for(int i=0;i<crds.size();i++) {
            double sum = 10000d;
            double fee = 0;
            Card card = crds.get(i);
            int j = -1;
            //get money from other cards if the available sum is not enough
            while (sum > card.getAvailable()) {
                // if it's the current card the fee does not change
                if (j == -1) {
                    sum -= card.getAvailable();
                    // increase j but make sure it will not equals i ( the current card index)
                    do {
                        j++;
                    } while (i == j);
                    card = crds.get(j);
                // otherwise the fee increase and all available sum is transfer to current card
                } else {
                    // calculate current fee, decrease the sum and increase the fee
                    double cfee = (card.getFee() / 100) * card.getAvailable();
                    sum -= (card.getAvailable() - cfee);
                    fee += cfee;

                    do {
                        j++;
                    } while (i == j);
                    card = crds.get(j);
                }
            }

            //sum to extract (it's available in the current card ( of index j ) )
            double exs = sum;
            //if it's not the current staring card and the money need to be transferred we transfer the
            //needed amount to cover the sum and the fee
            if (j != -1) {
                // calculate the amount needed to be transferred
                exs = (100 * sum) / (100 - card.getFee());
                // calculate the fee for that amount
                fee += exs * (card.getFee() / 100);
            }
            // put card and the computed costs in Map
            ret.put(crds.get(i), new Cost(fee, TVA));

        }

        return ret;
    }

    public static void main(String[] args) {
        Code c = new Code();
        c.cards.add(new Card("SILVER",0.2,2000,"23.05.2020",4000));
        c.cards.add(new Card("GOLD",0.1,2000,"15.08.2018",2000));
        c.cards.add(new Card("PLATINUM",0.3,2000,"20.03.2019",3000));
        c.cards.add(new Card("IRIDIUM",0.2,2000,"23.06.2020",5000));
        c.cards.add(new Card("BRONZE",0.5,2000,"15.07.2019",2500));
        c.cards.add(new Card("PREMIUM",0.15,2000,"20.08.2019",2000));

        Map<Card,Cost> all = c.getCardsCost();

        all.forEach((card,cost)->{
            System.out.println(card+"  "+cost);
        });

        System.out.println(today);
    }

}
