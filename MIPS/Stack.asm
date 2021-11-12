# 
#   
#   @author Ethan Cao
#   @version 
#

    .data   
        newLine: .asciiz "\n"
    .text 0x00400000
    .globl main

main:
    li $t1, 0
    move $t2, $sp
    li $v0, 5
    syscall
    beq $t1, $v0, loop
    addi $sp, $sp, -4
    sw $v0, 0($sp)
    j main
loop:
    lw $a0, 0($sp)
    addi $sp, $sp, 4
    beq $t1, $a0, end
    li $v0, 1
    syscall
    li $v0, 4
    la $a0, newLine
    syscall
    j loop
end:
    li $v0, 10
    syscall
    