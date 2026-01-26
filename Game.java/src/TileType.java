enum TileType {
    WALL {
        @Override
        public boolean isAccessibleBy(PlayerForm form) {
            return false;
        }
    },

    NEUTRAL {
        @Override 
        public boolean isAccessibleBy(PlayerForm form) {
            return true;
        }
    },
    BLACK {
        @Override
        public boolean isAccessibleBy(PlayerForm form) {
            return form == PlayerForm.BLACK;
        }
    },
    WHITE {
        @Override
        public boolean isAccessibleBy(PlayerForm form) {
            return form == PlayerForm.WHITE;
        }
    },
    GREY {
        @Override
        public boolean isAccessibleBy(PlayerForm form) {
            return form == PlayerForm.GREY;
        }
    },
    EXIT {
        @Override 
        public boolean isAccessibleBy(PlayerForm form) {
            return form == PlayerForm.GREY;
        }
    };
    public abstract boolean isAccessibleBy(PlayerForm form);
}
