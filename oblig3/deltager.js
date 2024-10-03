class DeltagerManager {
    // Deklarer felt-variabler her

    constructor(root) {
        this.root = root;
        this.deltagere = [];
        this.init(); //Kaller init-metoden for å sette opp hendelser
    }

    init() {
        const registrerButton = this.root.querySelector("button[type='button']")
        registrerButton.addEventListener("click", () => this.registrerDeltager());

        const visButton = this.root.querySelectorAll("button[type='button']")[1];
        visButton.addEventListener("click", () => this.visDeltagere());
    }

    registrerDeltager() {
        const startnummerInput = document.getElementById("startnummer");
        const deltagernavnInput = document.getElementById("deltagernavn");
        const sluttidInput = document.getElementById("sluttid");

        const startnummer = startnummerInput.value;
        const deltagernavn = deltagernavnInput.value;
        const sluttid = sluttidInput.value;

        //Sjekker om alle felt er fylt ut
        if(!startnummer || !deltagernavn || !sluttid){
            return; //Ikke registrert deltager
        }

        //Sjekker om startnummeret allerede finnes
        if(this.deltagere.some(d => d.startnummer === startnummer)){
            startnummerInput.setCustomValidity("Startnummer " + startnummer + " er allerede i bruk!");
            startnummerInput.focus();
            startnummerInput.reportValidity();
            return;
        }

        //Validerer deltagers navn
        const validNamePattern = /^\s*\p{L}{2,}((\s+|-)\p{L}{2,})*\s*$/u; //Gyldige tegn
        if(!validNamePattern.test(deltagernavn)) {
            deltagernavnInput.setCustomValidity("Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn.");
            deltagernavnInput.focus();
            deltagernavnInput.reportValidity();
            return;
        }

        //Formater navnet
        const formattedName = this.formatName(deltagernavn);

        //Legger deltager til i listen
        this.deltagere.push({
            startnummer: startnummer,
            navn: formattedName,
            sluttid: sluttid
        });

        //Oppdater bekreftelsesmeldingen
        const bekreftelse = this.root.querySelector(".hidden");
        bekreftelse.querySelector("span").textContent = formattedName;
        bekreftelse.querySelector("span:ntk-of-type(2)").textContent = startnummer;
        bekreftelse.querySelector("span:nth-f-type(3)").textContent = sluttid;
        bekreftelse.classList.remove("hidden");

        //Tømmer input-feltene og setter fokus på startnummer
        startnummerInput.value = "";
        deltagernavnInput.value = "";
        sluttidInput.value = "";
        startnummerInput.focus();
    }

    formatName(name) {
        return name
        .split(/\s+/) // Splitter ved mellomrom
        .map(part => part.trim()) // Fjerner unødvendige mellomrom
        .join(' '); // Setter sammen igjen med mellomrom
    }

    visDeltagere() {
        const nedregrense = document.getElementById("nedregrense").value;
        const ovregrense = document.getElementById("ovregrense").value;
        const tbody = this.root.querySelector("tbody");
        tbody.innerHTML = ""; //Tømmer tabellen før visning

        //Validerer tidsintervall
        if(nedregrense && ovregrense && nedregrense > ovregrense){
            const ovregrenseInput = document.getElementById("ovregrense");
            ovregrenseInput.setCustomValidity("Øvre grense kan ikke være mindre enn nedre grense.");
            ovregrenseInput.focus();
            ovregrenseInput.reportValidity();
            return;
        }

        //Filtrer og vis deltagere basert på sluttid
        const filtrerteDeltagere = this.deltagere.filter(deltager => {
            return (!nedregrense || deltager.sluttid >= nedregrense) && 
                    (!ovregrense || deltager.sluttid <= ovregrense);
        });

        //Dersom ingen filtrerte deltagere, vis melding
        if(filtrerteDeltagere.length === 0){
            const ingenResultat = this.root.querySelector(".liste p");
            ingenResultat.style.display = "block"; //Vis melding om ingen resultater
            return;
        } else{
            const ingenResultat = this.root.querySelector(".liste p");
            ingenResultat.style.display = "none"; //Skjul melding om ingen resultat
        }

        //Legge til hver deltager i tabellen
        filtrerteDeltagere.sort((a, b) => a.sluttid.localeCompare(b.sluttid)); //Sorter etter sluttid
        filtrerteDeltagere.forEach((deltager, index) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${index + 1}</td>
                <td>${deltager.startnummer}</td>
                <td>${deltager.navn}</td>
                <td>${deltager.sluttid}</td>
            `;
            tbody.appendChild(row);
        });
    }
}

const rootelement = document.getElementById("root");
new DeltagerManager(rootelement);