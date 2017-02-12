package at.jku.ssw.stategraph.providers;

public class Relation {
		private Object source, dest;
		private String transitionName;
		Relation(Object source, Object dest) {
			this.source = source;
			this.dest = dest;
		}
		public Object getSource() {
			return source;
		}
		
		public Object getDestination() {
			return dest;
		}
		
		public void setSource(Object src) {
			source = src;
		}
		
		public void setDest(Object dest) {
			this.dest = dest;
		}
		
		@Override
		public int hashCode() {
			return source.hashCode() ^ dest.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			 if ( this == obj )
			      return true;
			    if ( obj == null || (obj.getClass() != this.getClass()))
			      return false;
			 Relation r = (Relation) obj;
			 return this.source == r.source && this.dest == r.dest;
		}
		public String getTransitionName() {
			return transitionName;
		}
		public void setTransitionName(String transitionName) {
			this.transitionName = transitionName;
		}
		
	}