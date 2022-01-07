import { Genero } from "./genero.model";

export class Filme {
    id!: number;
    nome!: string;
    dataLancamento!: Date;
    imageUrl!: string;
    dataCadastro!: Date;
    genero!: Genero;
    descricao!: string;
    sinopse!: string;
}