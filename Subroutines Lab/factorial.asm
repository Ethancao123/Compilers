# 
#   
#   @author Ethan Cao
#   @version 
#
    .data   
        msg1: .asciiz "Input the value \n"
    .text 0x00400000
    .globl main

main:
    #prompts the user for input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $a0, $v0
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fact
    lw $ra, ($sp)
    addu $sp, $sp, 4
    move $a0, $v0
    #prints the result
    li $v0, 1
    syscall
end:
    li $v0, 10
    syscall

fact:
    beq $a0, 0, return
    move $t0, $a0
    subu $a0, $a0, 1
    subu $sp, $sp, 4
    sw $t0, ($sp)
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fact
    lw $ra, ($sp)
    addu $sp, $sp, 4
    lw $t0, ($sp)
    addu $sp, $sp, 4
    mult $t0, $v0
    mflo $v0
    jr $ra
return:
    li $v0, 1
    jr $ra
    