import { Column, Entity, Index, PrimaryGeneratedColumn } from "typeorm";

@Index("SYS_C007473", ["id"], { unique: true })
@Entity("USERS")
export class Users {
  @PrimaryGeneratedColumn({ type: "number", name: "ID" })
  id: number;

  @Column("varchar2", { name: "NAME", length: 50 })
  name: string;

  @Column("number", { name: "AGE" })
  age: number;
}
