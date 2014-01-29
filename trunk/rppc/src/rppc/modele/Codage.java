package rppc.modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Codage {

	private int permutationPlus[];
	private int permutationMoins[];

	
	public Codage(Codage codage) {
		this.permutationPlus = codage.permutationPlus.clone();
		this.permutationMoins = codage.permutationMoins.clone();
	}
	
	public Codage(Solution s) {
		this.permutationPlus = new int[s.getProbleme().getTaille()];
		this.permutationMoins = new int[s.getProbleme().getTaille()];

		List<List<Integer>> LA = new LinkedList<>();
		List<List<Integer>> RB = new LinkedList<>();
		for (int i = 0; i < s.getProbleme().getTaille(); i++) {
			LA.add(new LinkedList<Integer>());
			RB.add(new LinkedList<Integer>());

			for (int j = 0; j < s.getProbleme().getTaille(); j++) {
				if (s.getX(j) < s.getX(i) + s.getProbleme().getObjets().get(i).getLargeur()
						&& s.getY(j) + s.getProbleme().getObjets().get(j).getHauteur() > s.getY(i)) {
					LA.get(i).add(j);
					// RB.get(j).add(i);
				}

				if (s.getX(j) < s.getX(i) + s.getProbleme().getObjets().get(i).getLargeur()
						&& s.getY(j) < s.getY(i) + s.getProbleme().getObjets().get(i).getHauteur())
					RB.get(i).add(j);
			}
		}

		List<Integer> S = new ArrayList<>();
		List<Integer> S2 = new ArrayList<>();
		for (int i = 0; i < s.getProbleme().getTaille(); i++) {
			S.add(i);
			S2.add(i);
		}

		int l = 0;
		while (l < s.getProbleme().getTaille() && !S.isEmpty()) {

			int min = S.get(0);
			for (int i = 0; i < S.size(); i++) {

				if (interNonVide(S, LA.get(S.get(i)))) {
					if (LA.get(S.get(i)).size() <= LA.get(min).size())
						min = S.get(i);
				}
			}
			S.remove((Integer) min);
			permutationPlus[l] = min;
			l++;
		}

		l = 0;
		while (l < s.getProbleme().getTaille() && !S2.isEmpty()) {

			int min = S2.get(0);
			for (int i = 0; i < S2.size(); i++) {
				// System.out.println(S2.get(i) +" "+ RB.get(i) + " " +
				// LA.get(i));

				if (interNonVide(S2, RB.get(S2.get(i)))) {
					if (RB.get(S2.get(i)).size() <= RB.get(min).size())
						min = S2.get(i);
				}
			}
			// System.out.println("nn "+min + " "+S2);
			S2.remove((Integer) min);
			permutationMoins[l] = min;
			l++;
		}

	}

	private boolean interNonVide(List<Integer> s, List<Integer> list) {
		for (Integer i : s) {
			if (list.contains(i))
				return true;
		}
		return false;
	}

	public Codage(int taille) {
		this.permutationPlus = new int[taille];
		this.permutationMoins = new int[taille];
	}

	public Codage(int[] permutationPlus, int[] permutationMoins) {
		this.permutationPlus = permutationPlus;
		this.permutationMoins = permutationMoins;
	}

	public int[] getPermutationPlus() {
		return permutationPlus;
	}

	public void setPermutationPlus(int[] permutationPlus) {
		this.permutationPlus = permutationPlus;
	}

	public int[] getPermutationMoins() {
		return permutationMoins;
	}

	public void setPermutationMoins(int[] permutationMoins) {
		this.permutationMoins = permutationMoins;
	}

	public static Codage permutationEnonce() {
		int permutationPlus[] = new int[] { 0, 1, 2, 3, 4, 5 };
		int permutationMoins[] = new int[] { 3, 4, 0, 2, 5, 1 };

		Codage c = new Codage(permutationPlus, permutationMoins);
		return c;
	}

	public void afficher() {
		for (int i = 0; i < permutationPlus.length; i++) {
			System.out.print(permutationPlus[i] + " ");

		}
		System.out.println("");
		for (int i = 0; i < permutationPlus.length; i++) {
			System.out.print(permutationMoins[i] + " ");

		}
		System.out.println("");
	}

	public void shiftNeighborhood(int indice1, int indice2) {
		int tmp1 = 0, tmp2 = 0;
		tmp1 = permutationPlus[indice1];
		tmp2 = permutationMoins[indice1];
		permutationPlus[indice1] = permutationPlus[indice2];
		permutationMoins[indice1] = permutationMoins[indice2];
		permutationPlus[indice2] = tmp1;
		permutationMoins[indice2] = tmp2;

	}
}
